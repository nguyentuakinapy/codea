package codea.service;

import org.springframework.stereotype.Service;

import codea.entity.Role;

@Service
public interface RoleService {
	//của Mỵ từ đây
	Role findByName(String roleName);
}


