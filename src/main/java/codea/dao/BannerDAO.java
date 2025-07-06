package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Banner;

public interface BannerDAO extends JpaRepository<Banner, Integer> {
}
