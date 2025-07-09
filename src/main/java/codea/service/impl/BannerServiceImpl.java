package codea.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import codea.dao.BannerDAO;
import codea.entity.Banner;
import codea.service.BannerService;
import codea.utils.CloudinaryUtils;

@Service
public class BannerServiceImpl implements BannerService {
	
	@Autowired
	BannerDAO bannerDAO;
	@Autowired
	CloudinaryUtils cloudinaryUtils;

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
		Banner banner = bannerDAO.findById(id).get();
		String publicId = cloudinaryUtils.extractPublicId(banner.getBannerUrl());
		
		try {
			cloudinaryUtils.deleteImage(publicId);
		} catch (IOException e) {
			throw new RuntimeException("Lỗi khi xoá ảnh trên Cloudinary", e);
		}
		bannerDAO.deleteById(id);
	}
}
