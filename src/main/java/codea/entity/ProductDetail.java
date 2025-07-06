package codea.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Product_DetailID")
	Integer productDetailId;

	@ManyToOne
	@JoinColumn(name = "ProductID")
	@JsonBackReference
	Product product;
	
	@OneToMany(mappedBy = "productDetail")
	@JsonManagedReference
	List<ProductDetailSize> sizes;

	@OneToMany(mappedBy = "productDetail")
	@JsonManagedReference
	List<Gallery> galleries;
	
	@ManyToOne
    @JoinColumn(name = "ColorID", nullable = false)
	@JsonManagedReference
    Color color;
}
