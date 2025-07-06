package codea.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import codea.entity.Social;

@Service
public interface SocialService {
	List<Social> findAllSocial();
	
	Page<Social> findSocials(Pageable pageable);
	
	Social createSocial(Social social);
	
	Social updateSocial(Integer id, Social social);
	
	void deleteSocial(Integer id);
}
