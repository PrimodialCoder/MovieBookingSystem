package Repository;

import Entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater, Long> {

    Optional<List<Theater>> findByTheaterLocation(String location);
    // Additional query methods can be defined here if needed
}
