package com.nirbhay.bmm.businessserver.dao.impl;

import java.util.List;
import java.util.Optional;

import com.nirbhay.bmm.businessserver.dao.MovieShowDao;
import com.nirbhay.bmm.businessserver.repository.MovieShowRepository;
import com.nirbhay.bmm.model.bs.MovieShow;
import com.nirbhay.bmm.model.exception.MovieShowNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;



@Component
@Slf4j
public class MovieShowDaoImpl  implements MovieShowDao {

	private final MovieShowRepository repository;

    public MovieShowDaoImpl(MovieShowRepository repository) {
        this.repository = repository;
    }

    @Override
	public List<MovieShow> insertMovieShow(List<MovieShow> movieShow) {
		log.info("Saving movie show in db ");
		List<MovieShow> movieShows = repository.saveAll(movieShow);
		log.info("Saved successfully ");
		return movieShows;
	}

	@Override
	public MovieShow updateMovieShow(MovieShow movieShow) {
		log.info("Updating movie show in db for movie id {}", movieShow.getMovieShowId());
		MovieShow saved = repository.saveAndFlush(movieShow);
		log.info("Updated successfully {}", movieShow.getMovieShowId());
		return saved;
	}



	@Override
	public List<MovieShow> fetchAllShowsByTheatreId(long theatreId) {
		log.info("Finding all movie shows from db for theater id {}", theatreId);
		return repository.findAllByTheaterId(theatreId);
	}

	@Override
	public void logicalDelete(long movieShowId) {
		log.info("Logical deleting movie show from db for id {}", movieShowId);
		int logicalDelete = repository.logicalDelete(movieShowId);
		if(logicalDelete<=0){
			throw new MovieShowNotFoundException("Movie show is not found/ has booking, can't be deleted");
		}
		log.info("Deleting movie show for id {} is successful",movieShowId);

	}

	@Override
	public Optional<MovieShow> findById(long movieShowId) {
		log.info("Finding all movie shows from db for movieShowId  {}", movieShowId);
		return repository.findByMovieShowIdAndIsDeleted(movieShowId,0);
	}

}