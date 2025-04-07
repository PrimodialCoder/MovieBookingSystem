package Mapper;

import Entity.Movie;
import dto.MovieDto;

public class MovieMapper {
    public static MovieDto convertToMovieDto(Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getName(),
                movie.getDescription(),
                movie.getGenre(),
                movie.getLanguage(),
                movie.getReleaseDate(),
                movie.getDuration()
        );
    }
    public static Movie convertToMovieEntity(MovieDto movieDto) {
        return new Movie(
                movieDto.getId(),
                movieDto.getName(),
                movieDto.getDescription(),
                movieDto.getGenre(),
                movieDto.getLanguage(),
                movieDto.getReleaseDate(),
                movieDto.getDuration()
        );
    }
}
