package codea.dto;

import lombok.Data;

@Data
public class ProductPagingFilter {
	private Integer pageIndex;
    private Integer pageSize;
    private Integer categoryId;
}
