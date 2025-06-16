package mapogo.entity;

import java.text.DecimalFormat;
import java.util.List;

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
@Table(name = "product_detail_size")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailSize {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProductDetailSizeID")
	Integer product_detail_size_id;

	@Column(name = "Size")
	String size;
	
	@Column(name = "Quantity")
	Integer quantity;
	
	@Column(name = "Price")
	Double price;

	@OneToMany(mappedBy = "productDetailSize")
	List<Cart> carts;

	@OneToMany(mappedBy = "productDetailSize")
	List<OrderDetail> orderDetails;
}
