package codea.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codea.dto.PagedResponse;
import codea.entity.Category;
import codea.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/category")
@CrossOrigin("*")
@RestController
public class CategoryRestController {
	@Autowired
	CategoryService categoryService;

	@GetMapping
	public List<Category> getAllCategory() {
		return categoryService.findAllCategory();
	}
	
	@GetMapping("/page")
	public PagedResponse<Category> getCategoriesByPage(@RequestParam(defaultValue = "1" ) Integer pageIndex,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		int serverPageIndex = Math.max(pageIndex - 1, 0);
		Page<Category> categoryPage = categoryService.findCategories(PageRequest.of(serverPageIndex, pageSize));
	    return new PagedResponse<>(categoryPage.getTotalElements(), pageIndex, categoryPage.getSize(), categoryPage.getContent());
	}
	
	@PostMapping
	public Category createCategory(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}
	
	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
		return categoryService.updateCategory(id, category);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable("id") Integer id) {
		categoryService.deleteCategory(id);
	}
}


