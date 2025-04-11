package Service;

import Entity.Theater;
import Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dto.TheaterDto;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {
    //object of TheaterRepository
    @Autowired
    private TheaterRepository theaterRepository;

    public Theater addTheater(TheaterDto theaterDto) {
        Theater theater = new Theater();
        theater.setTheaterName(theaterDto.getTheaterName());
        theater.setTheaterLocation(theaterDto.getTheaterLocation());
        theater.setTheaterCapacity(theaterDto.getTheaterCapacity());
        theater.setTheaterScreenType(theaterDto.getTheaterScreenType());
        return theaterRepository.save(theater);
    }

    public List<Theater> getTheaterByLocation(String location) {
        Optional<List<Theater>> listOfTheaterBox = theaterRepository.findByTheaterLocation(location);
        if(listOfTheaterBox.isPresent()) {
            return listOfTheaterBox.get();
        } else {
            throw new RuntimeException("No theaters found in the specified location" + location);
        }
    }

    public Theater updateTheater(Long id, TheaterDto theaterDto){
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found with id: " + id));
        theater.setTheaterName(theaterDto.getTheaterName());
        theater.setTheaterLocation(theaterDto.getTheaterLocation());
        theater.setTheaterCapacity(theaterDto.getTheaterCapacity());
        theater.setTheaterScreenType(theaterDto.getTheaterScreenType());
        return theaterRepository.save(theater);
    }

    public void deleteTheater(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found with id: " + id));
        theaterRepository.delete(theater);//delete function takes the complete object of theater to delete and not just the id if we want to delete the theater by id then wuse deeleteById
    }
}
