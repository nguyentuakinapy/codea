package codea.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "[Orders]")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderID")
	Integer orderId;

	@Column(name = "Totalprice")
	Double totalPrice;

	@Temporal(TemporalType.DATE)
	@Column(name = "Date")
	LocalDate date = LocalDate.now();

	@Column(name = "Address")
	String address;

	@Column(name = "Phone")
	String phone;

	@Column(name = "Status")
	Integer status;

	@ManyToOne
	@JoinColumn(name = "UserID")
	User user;

	@ManyToOne
	@JoinColumn(name = "VoucherID")
	Voucher voucher;

	@OneToMany(mappedBy = "order")
	@JsonManagedReference
	List<OrderDetail> orderDetails;
	
	
}
