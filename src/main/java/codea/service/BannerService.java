package codea.service;

import java.util.List;

import org.springframework.stereotype.Service;

import codea.entity.Banner;

@Service
public interface BannerService {
	List<Banner> findAllBanner();
}
