package codea.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Order_Details")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderdetailID")
	Integer orderDetailId;

	@Column(name = "Quantity")
	Integer quantity;

	@ManyToOne
	@JoinColumn(name = "OrderID")
	@JsonBackReference
	Order order;

	@ManyToOne
	@JoinColumn(name = "ProductDetailSizeID")
	@JsonManagedReference
	ProductDetailSize productDetailSize;
}
