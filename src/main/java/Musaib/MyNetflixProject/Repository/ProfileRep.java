package Musaib.MyNetflixProject.Repository;

import Musaib.MyNetflixProject.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRep extends JpaRepository<Profile,Integer> {
}
