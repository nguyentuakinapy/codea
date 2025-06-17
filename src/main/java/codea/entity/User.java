package codea.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserID", nullable = false)
	private Integer userId;
	
	@Column(name = "Username", nullable = false)
	private String username;
	
	@Column(name = "Email", nullable = false)
	private String email;
	
	@Column(name = "Image", nullable = false)
	private String image;

	@Column(name = "Password", nullable = false)
	private String password;
	
	@Column(name = "Fullname", nullable = false)
	private String fullname;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) 
//	@JsonManagedReference
	private List<Authority> authorities;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) 
	@JsonIgnore
	private List<Address> address;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) 
	@JsonIgnore
	private List<Cart> carts;

}

