package codea.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codea.service.ProductService;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/home/")
public class HomeRestController {

	@Autowired
	ProductService productService;
	
	@GetMapping("products")
	public ResponseEntity<List<Map<String, Object>>> getHomeProducts() {
		return ResponseEntity.ok(productService.getProductsForHome());
	}
	
}
