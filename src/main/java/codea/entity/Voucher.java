package codea.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Voucher")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voucher {
	@Id
	@Column(name = "VoucherID")
	Integer voucherId;

	@Column(name = "PercentDecrease")
	Integer percentDecrease;

	@Column(name = "Name")
	String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "Date")
	LocalDate date = LocalDate.now();

	@OneToMany(mappedBy = "voucher")
	List<Order> orders;

}