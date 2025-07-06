package codea.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "product_detail_size")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailSize {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProductDetailSizeID")
	Integer productDetailSizeId;

	@Column(name = "Size", nullable = false)
	String size;

	@Column(name = "Quantity", nullable = false)
	Integer quantity;

	@Column(name = "Price", nullable = false)
	BigDecimal price;
	
	@Column(name = "discountPercent")
	Integer discountPercent = 0;
	
	@Column(name = "realPrice", nullable = false)
    private BigDecimal realPrice;

	@ManyToOne
	@JoinColumn(name = "Product_DetailID")
	@JsonBackReference
	ProductDetail productDetail;
	
	@OneToMany(mappedBy = "productDetailSize")
	@JsonBackReference
	List<Cart> carts;

	@OneToMany(mappedBy = "productDetailSize")
	@JsonBackReference
	List<OrderDetail> orderDetails;
}
