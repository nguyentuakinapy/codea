package mapogo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mapogo.entity.Voucher;

public interface VoucherDAO extends JpaRepository<Voucher, Integer>{

}
