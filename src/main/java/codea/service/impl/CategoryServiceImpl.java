package codea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codea.dao.CategoryDAO;
import codea.entity.Category;
import codea.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDAO categoryDAO;

	@Override
	public List<Category> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public Category findbyId(Integer Id) {
		return categoryDAO.findById(Id).get();
	}

	@Override
	public Category create(Category category) {
		return categoryDAO.save(category);
	}

	@Override
	public Category save(Category category) {
		return categoryDAO.save(category);
	}

	@Override
	public void delete(Category category) {
		categoryDAO.delete(category);
	}

}
