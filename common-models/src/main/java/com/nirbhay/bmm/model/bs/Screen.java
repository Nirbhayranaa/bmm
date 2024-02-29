package com.nirbhay.bmm.model.bs;


import com.nirbhay.bmm.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Screen extends BaseEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "screen_id")
	private Long screenId;

	@Column(name = "screen_name")
	private String screenName;

	@ManyToOne
	@JoinColumn(name = "theater_id")
	private Theater theater;

	@Column(name = "is_deleted")
	private int isDeleted;

}