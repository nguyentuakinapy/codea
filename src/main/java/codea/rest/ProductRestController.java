package codea.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codea.entity.Product;
import codea.entity.ProductDetail;
import codea.entity.ProductDetailSize;
import codea.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductRestController {
	@Autowired
	ProductService productService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Product product) {
		Product saved = productService.createProduct(product);
		return ResponseEntity.ok(saved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(productService.findById(id));
	}
}
