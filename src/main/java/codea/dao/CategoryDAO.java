package codea.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{

}
