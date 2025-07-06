package codea.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import codea.entity.Banner;

@Service
public interface BannerService {
	List<Banner> findAllBanner();
	
	Page<Banner> findBanners(Pageable pageable);
	
	Banner createBanner(Banner banner);
	
	Banner updateBanner(Integer id, Banner banner);
	
	void deleteBanner(Integer id);
}
