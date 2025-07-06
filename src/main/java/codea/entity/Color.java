package codea.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
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
@Table(name = "color")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Color {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ColorID")
	Integer colorId;
	
	@Column(name = "Name")
	String name;
	
	@Column(name = "Hexcode", length = 7)
	String hexCode;

	@OneToMany(mappedBy = "color", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
    private List<ProductDetail> productDetails;
}
