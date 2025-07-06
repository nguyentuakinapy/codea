package codea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
