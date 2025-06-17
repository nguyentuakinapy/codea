package codea.entity;

import java.util.Date;
import java.util.List;


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
	Integer productID;

	@Column(name = "Name")
	String name;

	@Column(name = "Status")
	Integer status = 1;

	@Temporal(TemporalType.DATE)
	@Column(name = "Date")
	Date date = new Date();

	@Column(name = "Description")
	String description;

	@ManyToOne
	@JoinColumn(name = "CategoryID")
	Category category;

	@ManyToOne
	@JoinColumn(name = "BrandID")
	Brand brand;

	@OneToMany(mappedBy = "product")
	List<Gallery> galleries;

	@OneToMany(mappedBy = "product")
	List<ProductDetail> productDetails;
//
//	@OneToMany(mappedBy = "product")
//	List<Feedback> feedbacks;
}
