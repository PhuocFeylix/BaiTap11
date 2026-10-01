package vn.feylix.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "author")
public class Author_24162099 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_id")
	private Long id;
	@Column(name = "author_name", nullable = false)
	private String name;
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	public Author_24162099() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long v) {
		id = v;
	}

	public String getName() {
		return name;
	}

	public void setName(String v) {
		name = v;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate v) {
		dateOfBirth = v;
	}
}
