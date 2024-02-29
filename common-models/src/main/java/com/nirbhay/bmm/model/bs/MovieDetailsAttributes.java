package com.nirbhay.bmm.model.bs;

import com.nirbhay.bmm.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@Entity
public class MovieDetailsAttributes extends  BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_details_attribute_id")
	private Long movieDetailsAttributeId;

	@Column(name = "movie_details_attribute_name")
	private String movieDetailsAttributeName;

	@Column(name = "movie_details_attribute_constant")
	private String movieDetailsAttributeConstant;

}