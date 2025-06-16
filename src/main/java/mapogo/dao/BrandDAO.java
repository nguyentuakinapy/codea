package mapogo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mapogo.entity.Brand;

public interface BrandDAO extends JpaRepository<Brand, Integer> {

}
