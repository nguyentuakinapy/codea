package codea.service;

import java.util.List;

import org.springframework.stereotype.Service;

import codea.entity.Category;

@Service
public interface CategoryService {
	
	List<Category> findAll();
	
	Category findbyId(Integer Id);
	
	Category create(Category category);
	
	Category save(Category category);
	
	void delete(Category category);
	
}
