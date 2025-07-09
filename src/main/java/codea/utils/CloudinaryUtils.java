package codea.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryUtils {
    @Autowired
    private Cloudinary cloudinary;

    // Phương thức để xóa ảnh
    public void deleteImage(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
    
    public String extractPublicId(String url) {
        // Tách phần cuối URL sau /upload/...
        String[] parts = url.split("/upload/");
        if (parts.length < 2) return null;

        // Lấy path sau /upload/, rồi loại phần mở rộng (.jpg, .png...)
        String pathWithVersion = parts[1];
        String[] segments = pathWithVersion.split("/");
        if (segments.length < 2) return null;

        // Bỏ phần version (v12345), lấy phần còn lại
        StringBuilder publicId = new StringBuilder();
        for (int i = 1; i < segments.length; i++) {
            publicId.append(segments[i]);
            if (i < segments.length - 1) {
                publicId.append("/");
            }
        }

        // Bỏ phần mở rộng (.jpg, .png...)
        int dotIndex = publicId.lastIndexOf(".");
        if (dotIndex != -1) {
            publicId = new StringBuilder(publicId.substring(0, dotIndex));
        }

        return publicId.toString();
    }
}