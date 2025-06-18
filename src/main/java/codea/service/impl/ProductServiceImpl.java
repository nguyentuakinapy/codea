package codea.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import codea.dao.ProductDAO;
import codea.entity.Brand;
import codea.entity.Product;
import codea.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDAO productDAO;
	
	@Override
	public Page<Product> findAllUserSoft(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return productDAO.findAll(pageable);
	}

	@Override
	public Product findbyId(Integer id) {
		return productDAO.findById(id).get();
	}

	@Override
	public List<Product> findByBrand(Brand brand) {
		return productDAO.findByBrand(brand);
	}

}
