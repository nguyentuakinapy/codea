package codea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import codea.dao.CategoryDAO;
import codea.entity.Category;
import codea.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDAO categoryDAO;

	@Override
	public List<Category> findAllCategory() {
		return categoryDAO.findAll();
	}

	@Override
	public Page<Category> findCategories(Pageable pageable) {
		return categoryDAO.findAll(pageable);
	}

	@Override
	public Category createCategory(Category category) {
		return categoryDAO.save(category);
	}

	@Override
	public Category updateCategory(Integer id, Category category) {
		Category existing = categoryDAO.findById(id).get();
		existing.setName(category.getName());
		return categoryDAO.save(existing);
	}

	@Override
	public void deleteCategory(Integer id) {
		categoryDAO.deleteById(id);
	}

}
