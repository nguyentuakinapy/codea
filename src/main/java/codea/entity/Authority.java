package codea.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authority")
@Data
public class Authority implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AuthorityId")
	private Integer authorityId;

	@ManyToOne
	@JoinColumn(name = "UserID")
	@JsonBackReference // Ngăn vòng lặp tuần tự hóa
	private User user;

	@ManyToOne
	@JoinColumn(name = "RoleID")
	private Role role;

}
