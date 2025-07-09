package codea.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codea.service.ProductDetailSizeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/size")
public class ProductDetailSizeRestController {
	@Autowired
	ProductDetailSizeService detailSizeService;
	
	@DeleteMapping("/{id}")
	void deleteSize(@PathVariable("id") Integer id) {
		detailSizeService.deleteSize(id);
	}
}
