package codea.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
	private Double totalPrice;
	private String address;
	private String phone;
	private Integer status;
	private Integer userId;
	private Integer voucherId;
	private List<OrderDetailRequest> orderDetails;
}
