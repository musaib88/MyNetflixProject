package Musaib.MyNetflixProject.Repository;

import Musaib.MyNetflixProject.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface ShowRep extends JpaRepository<Show,String> {
    @Query(value = "SELECT * FROM shows s WHERE s.name LIKE %:name%",
            nativeQuery = true)
    Set<Show> findByShowName(@Param("name")String name);
}
