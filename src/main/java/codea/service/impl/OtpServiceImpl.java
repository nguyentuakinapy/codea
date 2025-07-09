package codea.service.impl;

import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import codea.service.OtpService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
	private final JavaMailSender mailSender;

	@Override
	public String generateOtp(String email, String subject) {
		String otp = String.format("%04d", new Random().nextInt(10000));
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject(subject);
		message.setText("Mã OTP của bạn là: " + otp);
		mailSender.send(message);
		
		return otp;
	}

	@Override
	public void sendSimpleEmail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		 message.setTo(to);
	    message.setSubject(subject);
	    message.setText(content);
	    mailSender.send(message);
	}
}
