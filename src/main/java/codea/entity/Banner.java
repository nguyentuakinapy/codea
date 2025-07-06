package codea.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "banners")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner {
	@Id
	@Column(name = "BannerID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer bannerId;

	@Column(name = "BannerURL")
	String bannerUrl;
}
