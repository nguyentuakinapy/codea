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
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProductID")
	Integer productId;

	@Column(name = "Name", nullable = false)
	String name;

	@Column(name = "Status", nullable = false)
	Integer status = 1;

	@Temporal(TemporalType.DATE)
	@Column(name = "Date", nullable = false)
	LocalDate date = LocalDate.now();
	
	@Column(name = "Description", nullable = true)
	String description;
	
	@ManyToOne
	@JoinColumn(name = "CategoryID", nullable = false)
	@JsonManagedReference
	Category category;

	@OneToMany(mappedBy = "product")
	@JsonManagedReference
	List<ProductDetail> productDetails;

	@OneToMany(mappedBy = "product")
	@JsonManagedReference
	List<Feedback> feedbacks;
}
