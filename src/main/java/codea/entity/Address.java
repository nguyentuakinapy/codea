package codea.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AddressID")
	Integer addressId;

	@Column(name = "AddressDetail", nullable = false)
	String addressDetail;
	
	@Column(name = "Address", nullable = false)
	String address;

	@Column(name = "Phone", nullable = false, length = 10)
	String phone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserID")
	User user;

}
