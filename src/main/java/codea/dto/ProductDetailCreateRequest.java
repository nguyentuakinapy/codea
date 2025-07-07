package codea.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductDetailCreateRequest {
	@Data
    public static class GalleryDto {
        private String imageUrl;
    }
	
	@Data
    public static class ProductDetailSizeDto {
        private String size;
        private Integer quantity;
        private Double price;
        private Integer discountPercent;
    }
	
	private Integer productId;
	private Integer colorId;
	private List<GalleryDto> galleries;
	private List<ProductDetailSizeDto> sizes;
}
