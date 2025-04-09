package Controller;

import Entity.Movie;
import Service.MovieService;
import dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")

public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/addmovie")
    @PreAuthorize("hasRole('ADMIN')")
    //build REST API to create an entry in database it will take the request body and convert it to MovieDto object and send it to the service layer's addMovie method
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.addMovie(movieDto));

    }

    //Build REST API to get all the movies saved in the database in the form of ListDto
    @GetMapping("/getallmovies")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> allMovies = movieService.getAllMovies();
        if(allMovies.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
        }
        return ResponseEntity.ok(allMovies);
    }

    //
    @GetMapping("/getmoviebygenre")
    public ResponseEntity<List<Movie>> getAllMoviesByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(movieService.getMovieByGenre(genre));
    }

    @GetMapping("/getmoviebylanguage")
    public ResponseEntity<List<Movie>> getAllMoviesByLanguage(@RequestParam String language) {
        return ResponseEntity.ok(movieService.getMovieByLanguage(language));
    }

    @GetMapping("/getmoviebytitle")
    public ResponseEntity<Movie> getMovieByTitle(@RequestParam String title) {
        return ResponseEntity.ok(movieService.getMovieByTitle(title));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updatemovie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable long id, @RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.updateMovie(movieDto, id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }



}
