package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Brand;

public interface BrandDAO extends JpaRepository<Brand, Integer> {

}
