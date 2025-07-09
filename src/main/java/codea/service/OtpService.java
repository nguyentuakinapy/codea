package codea.service;

public interface OtpService {
	String generateOtp(String email, String subject);
	void sendSimpleEmail(String to, String subject, String content);
}
