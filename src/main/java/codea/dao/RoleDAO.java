package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Role;

public interface RoleDAO extends JpaRepository<Role, Integer>{
	
	Role findByName(String roleName);
}
