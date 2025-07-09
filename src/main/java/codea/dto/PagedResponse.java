package codea.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagedResponse<T> {
	long totalRecords;
	int totalPages;
	int pageIndex;
	int pageSize;
	List<T> records;
}
