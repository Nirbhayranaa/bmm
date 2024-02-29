package com.nirbhay.bmm.model.bs;

import com.nirbhay.bmm.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@Entity
public class MovieDetails extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_details_id")
	private Long movieDetailsId;
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@Column(name = "key_text")
	private String keyText;

	@Column(name = "value_text")
	private String valueText;

	@Column(name = "is_deleted")
	private int isDeleted;

	
}