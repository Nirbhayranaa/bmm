package com.nirbhay.bmm.model.bs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.nirbhay.bmm.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class MovieShow  extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_show_id")
	private Long movieShowId;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@ManyToOne
	@JoinColumn(name = "screen_id")
	private Screen screen;

	@Column(name = "show_timing_from")
	private LocalTime showTimingFrom;

	@Column(name = "show_timing_to")
	private LocalTime showTimingTo;

	@Column(name = "show_date")
	private LocalDate showDate;

	@Column(name = "is_deleted")
	private int isDeleted;


	@OneToMany(mappedBy = "movieShow", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("movieShow")
	private List<SeatPrice> seatPrice;

}