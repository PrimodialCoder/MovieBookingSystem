package dto;

import lombok.*;

import java.time.LocalDate;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
