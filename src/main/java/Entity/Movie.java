package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String genre;
    private String language;
    private LocalDate releaseDate;
    private Integer duration;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Show> show;

    public Movie(long id, String name, String description, String genre, String language, LocalDate releaseDate, Integer duration) {
    }
}
