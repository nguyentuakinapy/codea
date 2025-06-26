package codea.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codea.dto.ResponeBase;
import codea.dto.ResponeBasePage;
import codea.entity.Product;
import codea.entity.ProductDetail;
import codea.entity.ProductDetailSize;
import codea.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/product")
public class ProductRestController {

	@Autowired
	ProductService productService;

	@PostMapping("get/all")
	public ResponeBase<Map<String, Object>> findAll(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer size) {
		ResponeBase<Map<String, Object>> basePage = new ResponeBase<Map<String, Object>>();

		Page<Product> productPage = productService.findAllUserSoft(page, size);
		List<Product> products = productPage.getContent();
		basePage.setSuccess(true);
		basePage.setError("");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalPage", productPage.getTotalPages());
		map.put("pageSize", size);
		map.put("pageIndex", productPage.getNumber());
		map.put("records", products);
		basePage.setData(map);
		return basePage;
	}

	@GetMapping("/{id}")
	public ResponeBase<Map<String, Object>> findById(@PathVariable Integer id) {
		Product product = productService.findbyId(id);
		ResponeBase<Map<String, Object>> responeBase = new ResponeBase<Map<String, Object>>();
		responeBase.setSuccess(true);
		responeBase.setError("");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product", product);

		List<Product> list = productService.findByBrand(product.getBrand());

		map.put("listProduct", list.stream().limit(4).collect(Collectors.toList()));
		responeBase.setData(map);
		return responeBase;
	}

	@PostMapping("home")
	public ResponeBase<Map<String, Object>> productHome(@RequestParam("page") Integer page,
	                                                    @RequestParam("size") Integer size) {
	    ResponeBase<Map<String, Object>> basePage = new ResponeBase<>();
	    Page<Product> productPage = productService.findAllUserSoft(page, size);
	    List<Product> products = productPage.getContent();

	    basePage.setSuccess(true);
	    basePage.setError("");

	    Map<String, Object> map = new HashMap<>();
	    List<Map<String, Object>> productList = new ArrayList<>();

	    for (Product p : products) {
	        Map<String, Object> productMap = new HashMap<>();
	        productMap.put("productId", p.getProductID());
	        productMap.put("name", p.getName());
	        
	        List<ProductDetail> detailList = p.getProductDetails();
	        if (detailList != null && !detailList.isEmpty()) {
	            // Giá sản phẩm: lấy từ chi tiết đầu tiên
	            List<ProductDetailSize> sizeList = detailList.get(0).getProductDetailSize();
	            if (sizeList != null && !sizeList.isEmpty()) {
	                productMap.put("price", sizeList.get(0).getPrice());
	                productMap.put("discountPercent", sizeList.get(0).getDiscountPercent());
	                productMap.put("realPrice", sizeList.get(0).getRealPrice());
	            } else {
	                productMap.put("price", 0);
	            }

	            // Danh sách màu sắc và hình ảnh
	            List<Map<String, Object>> colorList = new ArrayList<>();
	            for (ProductDetail pd : detailList) {
	                Map<String, Object> colorMap = new HashMap<>();
	                colorMap.put("color", pd.getColor());
	                colorMap.put("image", pd.getImage());
	                colorList.add(colorMap);
	            }
	            productMap.put("colors", colorList);
	        } else {
	            productMap.put("price", 0);
	            productMap.put("colors", new ArrayList<>());
	        }

	        productList.add(productMap);
	    }

	    map.put("totalPage", productPage.getTotalPages());
	    map.put("pageSize", size);
	    map.put("pageIndex", productPage.getNumber());
	    map.put("records", productList);
	    basePage.setData(map);

	    return basePage;
	}


	@PostMapping("create")
	public ResponeBase<Product> createProduct(@RequestParam("name") String name) {
		return null;
	}

}
