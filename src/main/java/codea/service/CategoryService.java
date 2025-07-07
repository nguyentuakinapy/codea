package codea.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import codea.entity.Category;

@Service
public interface CategoryService {
	
	List<Category> findAllCategory();
	
	Page<Category> findCategories(Pageable pageable);
	
	Category createCategory(Category category);
	
	Category updateCategory(Integer id, Category category);
	
	void deleteCategory(Integer id);
	
}
