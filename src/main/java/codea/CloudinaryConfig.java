package codea;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class CloudinaryConfig {
	@Value("${CLOUDINARY_CLOUD_NAME}")
	private String cloudName;
	
	@Value("${CLOUDINARY_API_KEY}")
	private String apiKey = "636256514458547";
	
	@Value("${CLOUDINARY_API_SECRET}")
	private String apiSecret = "E_gZdeAUYsFKlWePqlEkZXiL5Ms";
	
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret
        ));
    }
}



