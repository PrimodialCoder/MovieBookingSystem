package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
// DTO (Data Transfer Object) for Movie entity
public class MovieDto {
    private long id;
    private String name;
    private String description;
    private String genre;
    private String language;
    private LocalDate releaseDate;
    private Integer duration;
}
