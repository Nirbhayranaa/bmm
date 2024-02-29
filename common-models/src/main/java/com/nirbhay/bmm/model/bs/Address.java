package com.nirbhay.bmm.model.bs;

import com.nirbhay.bmm.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Address extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long addressId;

	@Column(name = "address_lines")
	private String addressLines;

	@Column(name = "landmark")
	private String landmark;

	@Column(name = "pincode")
	private String pincode;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	@Column(name = "primary_phone")
	private String primaryPhone;

	@Column(name = "is_deleted")
	private int isDeleted;
}