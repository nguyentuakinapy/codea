package codea.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartResponse {
	private Integer cartId;
	private Integer quantity;

	private Integer productDetailSizeId;
	private String size;
	private BigDecimal price;
	private BigDecimal realPrice;

	private String colorName;
	private String productName;
	private String imageUrl;
}
