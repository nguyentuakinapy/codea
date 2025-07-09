package codea.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import codea.dao.SocialDAO;
import codea.entity.Social;
import codea.service.SocialService;
import codea.utils.CloudinaryUtils;

@Service
public class SocialServiceImpl implements SocialService {
	@Autowired
	SocialDAO socialDAO;
	@Autowired
	CloudinaryUtils cloudinaryUtils;
	
	@Override
	public List<Social> findAllSocial() {
		return socialDAO.findAll().stream().filter(Social::getIsActive).collect(Collectors.toList());
	}

	@Override
	public Page<Social> findSocials(Pageable pageable) {
		return socialDAO.findAll(pageable);
	}

	@Override
	public Social createSocial(Social social) {
		if (Boolean.TRUE.equals(social.getIsPhone()) && Boolean.TRUE.equals(social.getIsActive())) {
			List<Social> activePhones = socialDAO.findAllByIsPhoneTrueAndIsActiveTrue();
			for (Social phone: activePhones) {
				phone.setIsActive(false);
			}
			socialDAO.saveAll(activePhones);
		}
		return socialDAO.save(social);
	}

	@Override
	public Social updateSocial(Integer id, Social social) {
		Social existing = socialDAO.findById(id).get();
		
		if (Boolean.TRUE.equals(social.getIsPhone()) && Boolean.TRUE.equals(social.getIsActive())) {
			List<Social> activePhones = socialDAO.findAllByIsPhoneTrueAndIsActiveTrue();
			for (Social phone: activePhones) {
				if (!phone.getSocialId().equals(id)) {
					phone.setIsActive(false);
				}
			}
			socialDAO.saveAll(activePhones);
		}
		
		existing.setIconUrl(social.getIconUrl());
		existing.setIsActive(social.getIsActive());
		existing.setLink(social.getLink());
		existing.setName(social.getName());
		existing.setIsPhone(social.getIsPhone());
		return socialDAO.save(existing);
	}

	@Override
	public void deleteSocial(Integer id) {
		Social social = socialDAO.findById(id).get();
		
		String publicId = cloudinaryUtils.extractPublicId(social.getIconUrl());
		
		try {
			cloudinaryUtils.deleteImage(publicId);
		} catch (IOException e) {
			throw new RuntimeException("Lỗi khi xoá ảnh trên Cloudinary", e);
		}
		
		socialDAO.deleteById(id);
	}
}
