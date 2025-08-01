package codea.service.impl;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import codea.dao.CategoryDAO;
import codea.dao.ColorDAO;
import codea.dao.ProductDAO;
import codea.dto.ProductCreateBody;
import codea.dto.ProductGroupByLabelDTO;
import codea.dto.ProductWithImagesDTO;
import codea.entity.Category;
import codea.entity.Color;
import codea.entity.Gallery;
import codea.entity.Product;
import codea.entity.ProductDetail;
import codea.entity.ProductDetailSize;
import codea.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO productDAO;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	ColorDAO colorDAO;

	@Override
	public List<Map<String, Object>> getProductsForHome() {
	    List<Map<String, Object>> result = new ArrayList<>();

	    List<Category> categories = categoryDAO.findAll();
	    for (Category category : categories) {
	        List<Product> products = productDAO.findTop8ByCategoryCategoryIdAndStatusOrderByDateDesc(category.getCategoryId(), 1);

	        List<Map<String, Object>> productList = products.stream().map(product -> {
	            Map<String, Object> map = new LinkedHashMap<>();
	            map.put("productId", product.getProductId());
	            map.put("name", product.getName());

	            List<ProductDetail> details = product.getProductDetails();
	            Optional<ProductDetail> detailWithBestPrice = details.stream()
	                .filter(d -> d.getSizes().stream().anyMatch(s -> s.getQuantity() > 0))
	                .min(Comparator.comparing(d -> d.getSizes().stream().filter(s -> s.getQuantity() > 0)
	                		.map(ProductDetailSize::getRealPrice).min(Comparator.naturalOrder())
	                        .orElse(BigDecimal.valueOf(Long.MAX_VALUE))
	                ));

	            if (detailWithBestPrice.isPresent()) {
	                ProductDetail detail = detailWithBestPrice.get();

	                Optional<ProductDetailSize> bestSize = detail.getSizes().stream().filter(s -> s.getQuantity() > 0)
	                    .min(Comparator.comparing(ProductDetailSize::getRealPrice));

	                bestSize.ifPresent(size -> {
	                    map.put("realPrice", size.getRealPrice());
	                    map.put("price", size.getPrice());
	                });

	                List<String> images = detail.getGalleries().stream().map(Gallery::getImageUrl).limit(2).toList();
	                map.put("images", images);
	            }

	            return map;
	        }).filter(m -> m.containsKey("realPrice")).toList();

	        Map<String, Object> categoryBlock = new LinkedHashMap<>();
	        categoryBlock.put("label", category.getName());
	        categoryBlock.put("products", productList);
	        result.add(categoryBlock);
	    }

	    return result;
	}

	@Override
	public ProductGroupByLabelDTO getProductSale() {
		List<Product> products = productDAO.findByStatus(1); // hoặc custom query

	    List<ProductWithImagesDTO> promotedProducts = products.stream().map(product -> {
	    	 for (ProductDetail detail : product.getProductDetails()) {
	                Optional<ProductDetailSize> bestSize = detail.getSizes().stream()
	                    .filter(s -> s.getQuantity() > 0 && s.getDiscountPercent() > 0)
	                    .min(Comparator.comparing(ProductDetailSize::getRealPrice));

	                if (bestSize.isPresent()) {
	                    List<String> images = detail.getGalleries().stream().map(Gallery::getImageUrl).limit(2).toList();

	                    ProductDetailSize size = bestSize.get();

	                    return new ProductWithImagesDTO(
	                        product.getProductId(),
	                        product.getName(),
	                        size.getRealPrice(),
	                        size.getPrice(),
	                        images
	                    );
	                }
	            }
	            return null;
	        }).filter(Objects::nonNull).toList();

	    return new ProductGroupByLabelDTO("Khuyến mãi", promotedProducts);
	}
	
	@Override
	public Page<Product> findProducts(Integer categoryId, Pageable pageable) {
		if (categoryId != null) {
	        return productDAO.findByCategoryCategoryId(categoryId, pageable);
	    }
	    return productDAO.findAll(pageable);
	}

	@Override
	public Product createProduct(ProductCreateBody body) {
		Category category = categoryDAO.findById(body.getCategoryId())
		        .orElseThrow(() -> new RuntimeException("Category not found"));

		Product product = new Product();
		product.setName(body.getName());
		product.setStatus(body.getStatus());
		product.setDate(body.getDate() != null ? body.getDate() : LocalDate.now());
		product.setCategory(category);
		return productDAO.save(product);
	}

	@Override
	public Product findById(Integer id) {
		return productDAO.findById(id).get();
	}

	@Override
	public Product updateProduct(Integer id, ProductCreateBody body) {
		Product existing = productDAO.findById(id).get();
		Category category = categoryDAO.findById(body.getCategoryId()).orElseThrow();
		existing.setCategory(category);
		existing.setName(body.getName());
	    existing.setStatus(body.getStatus());
	    existing.setDate(body.getDate() != null ? body.getDate() : LocalDate.now());
	    existing.setDescription(body.getDescription());
		return productDAO.save(existing);
	}
}
