package codea.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductCreateBody {
	private String name;
    private Integer status;
    private LocalDate date;
    private Integer categoryId;
}
