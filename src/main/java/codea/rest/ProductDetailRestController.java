package codea.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codea.dto.ProductDetailCreateRequest;
import codea.dto.ProductDetailUpdateRequest;
import codea.service.ProductDetailService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product-detail")
public class ProductDetailRestController {
	@Autowired
	ProductDetailService detailService;
	
	@PostMapping
	public ResponseEntity<?> createDetail(@RequestBody ProductDetailCreateRequest body) {
		return ResponseEntity.ok(detailService.create(body));
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody ProductDetailUpdateRequest request) {
	    return ResponseEntity.ok(detailService.update(request));
	}
	
	@DeleteMapping("/{id}")
	void deleteDetail(@PathVariable("id") Integer id) {
		detailService.delete(id);
	}
}
