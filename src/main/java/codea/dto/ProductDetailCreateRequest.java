package codea.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDetailCreateRequest {
    private Integer productId;
    private ColorRequest color;
    private List<SizeRequest> sizes;
    private List<GalleryRequest> galleries;

    @Data
    public static class ColorRequest {
        private String name;
        private String hexCode;
    }

    @Data
    public static class SizeRequest {
        private String size;
        private Integer quantity;
        private BigDecimal price;
        private Integer discountPercent;
        private BigDecimal realPrice;
    }

    @Data
    public static class GalleryRequest {
        private String imageUrl;
    }
}