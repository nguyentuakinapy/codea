package codea.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codea.dto.PagedResponse;
import codea.entity.Banner;
import codea.service.BannerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/banner")
public class BannerRestController {

	@Autowired
	BannerService bannerService;
	
	@GetMapping
	public List<Banner> getAllBanner() {
		return bannerService.findAllBanner();
	}
	
	@PostMapping("/page")
	public PagedResponse<Banner> getBannersByPage(@RequestParam(defaultValue = "1" ) Integer pageIndex,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		int serverPageIndex = Math.max(pageIndex - 1, 0);
		Page<Banner> bannerPage = bannerService.findBanners(PageRequest.of(serverPageIndex, pageSize));
	    return new PagedResponse<>(bannerPage.getTotalElements(), bannerPage.getTotalPages(), pageIndex,
	    		bannerPage.getSize(), bannerPage.getContent());
	}
	
	@PostMapping
	public Banner createBanner(@RequestBody Banner banner) {
		return bannerService.createBanner(banner);
	}
	
	@PutMapping("/{id}")
	public Banner updateBanner(@PathVariable("id") Integer id, @RequestBody Banner banner) {
		return bannerService.updateBanner(id, banner);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteBanner(@PathVariable("id") Integer id) {
		bannerService.deleteBanner(id);
	}
}
