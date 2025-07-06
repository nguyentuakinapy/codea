package codea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import codea.dao.SocialDAO;
import codea.entity.Social;
import codea.service.SocialService;

@Service
public class SocialServiceImpl implements SocialService {
	@Autowired
	SocialDAO socialDAO;
	
	@Override
	public List<Social> findAllSocial() {
		return socialDAO.findAll();
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
		socialDAO.deleteById(id);
	}
}
