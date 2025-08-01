package codea.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RoleID", nullable = false)
	private Integer roleId;

	@Column(name = "RoleName", nullable = false)
	private String roleName;

	@OneToMany(mappedBy = "role")
    @JsonIgnore
	private List<Authority> authorities;

}
