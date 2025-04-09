package Service;

import Entity.Movie;
import Mapper.MovieMapper;
import Repository.MovieRepository;
import dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    //addMovie is getting movieDTO from controller and converting it to movie entity and saving it to database using jpaRepository function .save it returns movie object then we return the response in the form of movieDTO
    public MovieDto addMovie(MovieDto movieDto) {
        Movie movie= MovieMapper.convertToMovie(movieDto);
        Movie savedMovie = movieRepository.save(movie);
        return MovieMapper.convertToMovieDto(savedMovie);
    }

    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(MovieMapper::convertToMovieDto)
                .toList();
    }


    public List<Movie> getMovieByGenre(String genre) {
        Optional<List<Movie>> listOfMovieDtoBox = movieRepository.findByGenre(genre);
        if (listOfMovieDtoBox.isPresent()) {
            return listOfMovieDtoBox.get();
        } else {
            throw new RuntimeException("No movies found with genre: " + genre);
        }
    }

    public List<Movie> getMovieByLanguage(String language) {
        Optional<List<Movie>> listOfMovieBox = movieRepository.findByLanguage(language);
        if (listOfMovieBox.isPresent()) {
            return listOfMovieBox.get();
        } else {
            throw new RuntimeException("No movies found of language: " + language);
        }
    }

    public Movie getMovieByTitle(String title) {
        Optional<Movie> movieBox = movieRepository.findByName(title);
        if (movieBox.isPresent()) {
            return movieBox.get();
        } else {
            throw new RuntimeException("No movies found of title: " + title);
        }
    }

    public Movie updateMovie(MovieDto movieDto, Long id) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(
                () -> new RuntimeException("Movie not found with id: " + id)
        );
        existingMovie.setName(movieDto.getName());
        existingMovie.setDescription(movieDto.getDescription());
        existingMovie.setGenre(movieDto.getGenre());
        existingMovie.setLanguage(movieDto.getLanguage());
        existingMovie.setReleaseDate(movieDto.getReleaseDate());
        existingMovie.setDuration(movieDto.getDuration());

        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Movie not found with id: " + id)
                );
        movieRepository.delete(movie);
    }
}
