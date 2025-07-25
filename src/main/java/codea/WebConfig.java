package codea;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Thay đổi theo đường dẫn API của bạn
        .allowedOrigins("http://localhost:5173",
        		"https://codea-fnv8.vercel.app")  // Thay đổi nếu cần
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các phương thức cho phép
        .allowedHeaders("*"); // Cho phép tất cả các headers
    }
}