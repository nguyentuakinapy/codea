package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Address;

public interface AddressDAO extends JpaRepository<Address, Integer>{

}
