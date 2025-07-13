package codea.entity;

import java.time.LocalDate;

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
@Table(name = "Feedback")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FeedbackID")
	Integer feedBackId;

	@Column(name = "Comment")
	String comment;

	@Column(name = "Star")
	Integer star;

	@Column(name = "Date")
	LocalDate date;

	@ManyToOne
	@JoinColumn(name = "UserID")
	User user;

	@ManyToOne
	@JoinColumn(name = "ProductID")
	@JsonBackReference
	Product product;
}
