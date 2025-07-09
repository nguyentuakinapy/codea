package codea.dto;

import lombok.Data;

@Data
public class OrderDetailRequest {
	private Integer productDetailSizeId;
    private Integer quantity;
}