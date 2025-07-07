package codea.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import codea.entity.User;

public interface UserService {
	List<User> findAllUser();
	
	Optional<User> findByEmail(String email);

	User register(User user);
	
	User update(User user);
	
	Map<String, Object> login(String email, String password);
    void sendOtpForRegister(User user);
    void verifyOtpForRegister(String email, String otp);
    void sendOtpForReset(String email);
    void resetPassword(String email, String otp, String newPassword);
}
