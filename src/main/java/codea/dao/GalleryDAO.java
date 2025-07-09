package codea.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Gallery;
import codea.entity.ProductDetail;

public interface GalleryDAO extends JpaRepository<Gallery, Integer>{
	List<Gallery> findByProductDetail(ProductDetail detail);
}
