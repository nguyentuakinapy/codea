package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Voucher;

public interface VoucherDAO extends JpaRepository<Voucher, Integer>{

}
