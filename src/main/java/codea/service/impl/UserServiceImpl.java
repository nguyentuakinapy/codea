package codea.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import codea.dao.RoleDAO;
import codea.dao.UserDAO;
import codea.entity.Authority;
import codea.entity.User;
import codea.service.OtpService;
import codea.service.UserService;
import codea.utils.OtpTempStorageService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	@Autowired
	UserDAO userDao;
	@Autowired
	RoleDAO roleDAO;
	@Autowired
	OtpService otpService;
	@Autowired
	OtpTempStorageService otpTempStorageService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> findAllUser() {
		return userDao.findAll();
	}
	
	@Override
	public Optional<User> findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public User register(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}

	@Override
	public User update(User user) {
		return userDao.save(user);
	}

	@Override
	public Map<String, Object> login(String email, String password) {
		User user = findByEmail(email).orElseThrow(() -> new RuntimeException("Email không tồn tại!"));
		if (!passwordEncoder.matches(password, user.getPassword())) throw new RuntimeException("Sai tài khoản hoặc mật khẩu!");
		return Map.of("userId", user.getUserId(), "fullname", user.getFullname(), "email", user.getEmail());
	}

	@Override
	public void sendOtpForRegister(User user) {
		if (findByEmail(user.getEmail()).isPresent()) throw new RuntimeException("Email đã tồn tại!");
		
		user.setImage(null);
		String otp = otpService.generateOtp(user.getEmail(), "Mã xác nhận đăng ký");
		otpTempStorageService.saveOtp(user.getEmail(), user, otp);
	}

	@Override
	public void verifyOtpForRegister(String email, String otp) {
		var entry = otpTempStorageService.getOtp(email);
		if (entry == null) throw new RuntimeException("Không tìm thấy OTP");
        if (!entry.getOtp().equals(otp)) throw new RuntimeException("Mã OTP không đúng");
        if (entry.getExpiredAt().isBefore(LocalDateTime.now())) {
            otpTempStorageService.removeOtp(email);
            throw new RuntimeException("Mã OTP đã hết hạn");
        }
        
        User user = entry.getUser();
        Authority authority = new Authority();
        authority.setUser(user);
        authority.setRole(roleDAO.findById(1).get());
        user.setAuthorities(List.of(authority));
        
        register(user);
        otpTempStorageService.removeOtp(email);
	}

	@Override
	public void sendOtpForReset(String email) {
		User user = findByEmail(email).orElseThrow(() -> new RuntimeException("Email không tồn tại!"));
		String otp = otpService.generateOtp(email, "Quên mật khẩu");
		otpTempStorageService.saveOtp(email, user, otp);
	}

	@Override
	public void resetPassword(String email, String otp, String newPassword) {
		var entry = otpTempStorageService.getOtp(email);
		if (entry == null || !entry.getOtp().equals(otp) || entry.getExpiredAt().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("OTP không hợp lệ hoặc đã hết hạn!");
		}
		
		User user = entry.getUser();
		user.setPassword(passwordEncoder.encode(newPassword));
		update(user);
		otpTempStorageService.removeOtp(email);
	}
}
