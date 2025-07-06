package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Color;

public interface ColorDAO extends JpaRepository<Color, Integer> {

}
