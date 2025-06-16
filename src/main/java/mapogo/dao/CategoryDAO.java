package mapogo.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import mapogo.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{

}
