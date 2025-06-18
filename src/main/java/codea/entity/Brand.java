package codea.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brand")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
	@Id
	@Column(name = "BrandID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer brandID;

	@Column(name = "Name")
	String name;

	@Column(name = "Address")
	String addRess;

	@Column(name = "Phone")
	String phone;

	@Column(name = "Email")
	String email;

	@OneToMany(mappedBy = "brand")
	@JsonBackReference
	List<Product> products;
}
