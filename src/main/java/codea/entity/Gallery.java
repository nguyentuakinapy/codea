package codea.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "gallery")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Gallery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GalleryID")
	Integer galleryID;

	@Column(name = "ImageURL")
	String imageUrl;

	@ManyToOne
	@JoinColumn(name = "ProductDetailID")
	@JsonBackReference
	ProductDetail productDetail ;
}
