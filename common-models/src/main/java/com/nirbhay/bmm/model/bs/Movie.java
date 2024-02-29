package com.nirbhay.bmm.model.bs;

import com.nirbhay.bmm.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Movie extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private Long movieId;

	@Column(name = "movie_name")
	private String movieName;

	@Column(name = "release_date")
	private Date releaseDate;

	@Column(name = "is_deleted")
	private int isDeleted;

	@PrePersist
	public void prePersist() {
		this.movieName = this.movieName.toLowerCase();
	}


	/*private Map<String, List<MovieDetails>> movieDetailsMap;


	public Map<String, List<MovieDetails>> getMovieDetailsMap() {

		Map<String, List<MovieDetails>> map = null;

		if(this.movieDetails!=null && !this.movieDetails.isEmpty()) {
			map = new HashMap<>();

			for(MovieDetails details : this.movieDetails)
			{
				if(map.containsKey(details.getMovieDetailsAttributes().getAttributeConstant())) {
					List<MovieDetails> li = map.get(details.getMovieDetailsAttributes().getAttributeConstant());
					li.add(details);
				}
				else {
					List<MovieDetails> li = new ArrayList<>();
					li.add(details);
					map.put(details.getMovieDetailsAttributes().getAttributeConstant(), li);
				}
			}
		}

		this.movieDetailsMap = map;

		return movieDetailsMap;
	}*/

}