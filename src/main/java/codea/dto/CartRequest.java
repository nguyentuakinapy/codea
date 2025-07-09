package codea.dto;

import lombok.Data;

@Data
public class CartRequest {
	private Integer userId;
	private Integer productDetailSizeId;
	private Integer quantity;
}
