package Musaib.MyNetflixProject.Repository;

import Musaib.MyNetflixProject.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRep extends JpaRepository<Series,String> {
}
