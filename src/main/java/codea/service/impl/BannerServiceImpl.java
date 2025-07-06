package codea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import codea.dao.BannerDAO;
import codea.entity.Banner;
import codea.service.BannerService;

@Service
public class BannerServiceImpl implements BannerService {
	
	@Autowired
	BannerDAO bannerDAO;

	@Override
	public List<Banner> findAllBanner() {
		return bannerDAO.findAll();
	}

	@Override
	public Page<Banner> findBanners(Pageable pageable) {
		return bannerDAO.findAll(pageable);
	}

	@Override
	public Banner createBanner(Banner banner) {
		return bannerDAO.save(banner);
	}
	
	@Override
	public Banner updateBanner(Integer id, Banner banner) {
		Banner existing = bannerDAO.findById(id).get();
		existing.setBannerUrl(banner.getBannerUrl());
		return bannerDAO.save(existing);
	}
	
	@Override
	public void deleteBanner(Integer id) {
		bannerDAO.deleteById(id);
	}
}
