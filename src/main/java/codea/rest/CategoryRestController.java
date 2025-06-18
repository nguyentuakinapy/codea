package codea.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import codea.dto.ResponeBase;
import codea.entity.Category;
import codea.entity.Product;
import codea.service.CategoryService;
import jakarta.mail.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/rest/category")
@CrossOrigin("*")
@RestController
public class CategoryRestController {
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/find/all")
	public ResponeBase<List<Category>> findAll() {
		ResponeBase<List<Category>> responeBase = new ResponeBase<List<Category>>();
		responeBase.setData(categoryService.findAll());
		responeBase.setError("");
		responeBase.setSuccess(true);
		return responeBase;
	}

	@GetMapping("/{id}")
	public ResponeBase<Category> findCategoryProductById(@PathVariable Integer id) {
		ResponeBase<Category> responeBase = new ResponeBase<Category>();
		responeBase.setData(categoryService.findbyId(id));
		responeBase.setSuccess(true);
		responeBase.setError("");
		return responeBase;
	}

//	@PostMapping("/create") // create
//	public CategoryProduct createCategoryProduct(@RequestParam("category") String categoryProductJson,
//			@RequestPart("fileImage") MultipartFile file) throws IOException {
//		// parse categoryProductJson thành đối tượng CategoryProduct
//		ObjectMapper objectMapper = new ObjectMapper();
//		CategoryProduct categoryProduct;
//		try {
//			categoryProduct = objectMapper.readValue(categoryProductJson, CategoryProduct.class);
//		} catch (Exception e) {
//			throw new Error("Có lỗi xảy ra khi chuyển đổi categoryProductJson");
//		}
//		// resolve file image
//		if (!file.isEmpty()) {
//			Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//			// Lấy URL của ảnh từ kết quả upload
//			String imageUrl = (String) uploadResult.get("secure_url");
//			categoryProduct.setImage(imageUrl);
//		}
//		return categoryProductService.createCategoryProduct(categoryProduct);
//	}
//
//	@DeleteMapping("/delete/{id}")
//	public void deleteCategoryProduct(@PathVariable("id") Integer id) {
//		categoryProductService.deleteCategoryProduct(id);
//	}
}


