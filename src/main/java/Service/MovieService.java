package Service;

import Entity.Movie;
import Repository.MovieRepository;
import dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(MovieDto movieDto) {

        return movieRepository.save(convertToEntity(movieDto));
    }

}
