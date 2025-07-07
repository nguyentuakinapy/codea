package codea.utils;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import codea.entity.User;
import lombok.Data;

@Service
public class OtpTempStorageService {
	@Data
	public static class OtpEntry {
		private User user;
		private String otp;
		private LocalDateTime expiredAt;
	}
	
	private final Map<String, OtpEntry> otpMap = new ConcurrentHashMap<>();
	
	public void saveOtp(String email, User user, String otp) {
		OtpEntry entry = new OtpEntry();
		entry.setUser(user);
		entry.setOtp(otp);
		entry.setExpiredAt(LocalDateTime.now().plusMinutes(5));
		otpMap.put(email, entry);
	}
	
	public boolean verifyOtp(String email, String inputOtp) {
	    OtpEntry entry = otpMap.get(email);
	    if (entry == null) return false;

	    if (LocalDateTime.now().isAfter(entry.getExpiredAt())) {
	        otpMap.remove(email);
	        return false;
	    }

	    boolean matched = entry.getOtp().equals(inputOtp);
	    if (matched) otpMap.remove(email);
	    return matched;
	}
	
	public OtpEntry getOtp(String email) {
		return otpMap.get(email);
	}
	
	public void removeOtp(String email) {
		otpMap.remove(email);
	}
}
