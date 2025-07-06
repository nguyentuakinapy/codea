package codea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codea.dao.AuthorityDAO;
import codea.dao.RoleDAO;
import codea.entity.Role;
import codea.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDAO roleDao;

	@Autowired
	AuthorityDAO authorityDAO;

	@Override
	public Role findByName(String roleName) {
		return roleDao.findByRoleName(roleName);
	}


}


