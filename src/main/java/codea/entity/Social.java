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
@Table(name = "socials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Social {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SocialID")
	Integer socialId;
	
	@Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "IconURL", nullable = false)
    private String iconUrl;

    @Column(name = "Link", nullable = false)
    private String link;

    @Column(name = "IsActive")
    private Boolean isActive;
}
