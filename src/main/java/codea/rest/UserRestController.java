package codea.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import codea.dto.ChangePasswordRequest;
import codea.dto.LoginRequest;
import codea.entity.User;
import codea.service.UserService;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

	@Autowired
	UserService userService;

	@GetMapping()
	public List<User> getFullUser() {
		return userService.findAllUser();
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		try {
			return ResponseEntity.ok(userService.login(request.getEmail(), request.getPassword()));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));
		}
	}
	
	@PostMapping("/register/send-otp")
	public ResponseEntity<?> sendOtpRegister(@RequestBody User user) {
		try {
            userService.sendOtpForRegister(user);
            return ResponseEntity.ok(Map.of("message", "Đã gửi mã OTP đến email"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
	}
	
	@PostMapping("/register/verify")
	public ResponseEntity<?> verifyRegister(@RequestParam String email, @RequestParam String otp) {
		try {
            userService.verifyOtpForRegister(email, otp);
            return ResponseEntity.ok(Map.of("message", "Đăng ký thành công!"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
	}

	@PostMapping("/forgot-password/send-otp")
	public ResponseEntity<?> forgotPassword(@RequestBody String email) {
		try {
            userService.sendOtpForReset(email);
            return ResponseEntity.ok(Map.of("message", "Mã OTP đã được gửi qua email"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
	}
	
	@PostMapping("/forgot-password/reset")
	public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> payload) {
		try {
            userService.resetPassword(payload.get("email"), payload.get("otp"), payload.get("newPassword"));
            return ResponseEntity.ok(Map.of("message", "Mật khẩu đã được cập nhật."));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
	}
	
	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
	    try {
	        userService.changePassword(request.getEmail(), request.getOldPassword(), request.getNewPassword());
	        return ResponseEntity.ok(Map.of("message", "Đổi mật khẩu thành công!"));
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
	    }
	}
}
