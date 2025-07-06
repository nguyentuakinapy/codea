package codea.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codea.dto.PagedResponse;
import codea.entity.Social;
import codea.service.SocialService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/social")
public class SocialRestController {
	@Autowired
	SocialService socialService;
	
	@GetMapping
	public List<Social> getAllSocial() {
		return socialService.findAllSocial();
	}
	
	@GetMapping("/page")
	public PagedResponse<Social> getSocialByPage(@RequestParam(defaultValue = "1") Integer pageIndex,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		int serverPageIndex = Math.max(pageIndex - 1, 0);
		Page<Social> socialPage = socialService.findSocials(PageRequest.of(serverPageIndex, pageSize));
		return new PagedResponse<>(socialPage.getTotalElements(), pageIndex, socialPage.getSize(), socialPage.getContent());
	}
	
	@PostMapping
	public Social createSocial(@RequestBody Social social) {
		return socialService.createSocial(social);
	}
	
	@PutMapping("/{id}")
	public Social updateSocial(@PathVariable("id") Integer id, @RequestBody Social social) {
		return socialService.updateSocial(id, social);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSocial(@PathVariable("id") Integer id) {
		socialService.deleteSocial(id);
	}
}
