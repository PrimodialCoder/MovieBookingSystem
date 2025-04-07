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
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.addMovie(movieDto));

    }

    @GetMapping("/getallmovies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/getmoviebygenre")
    public ResponseEntity<List<Movie>> getAllMoviesByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(movieService.getMovieByGenre(genre));
    }

    @GetMapping("/getmoviebylanguage")
    public ResponseEntity<List<Movie>> getAllMoviesByLanguage(@RequestParam String language) {
        return ResponseEntity.ok(movieService.getMovieByLanguage(language));
    }

    @GetMapping("/getmoviebytitle")
    public ResponseEntity<List<Movie>> getAllMoviesByTitle(@RequestParam String title) {
        return ResponseEntity.ok(movieService.getMovieByTitle(title));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updatemovie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable long id, @RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.updateMovie(movieDto, id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deletemovie/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }



}
