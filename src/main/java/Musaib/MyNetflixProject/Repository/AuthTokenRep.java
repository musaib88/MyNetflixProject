package Musaib.MyNetflixProject.Repository;

import Musaib.MyNetflixProject.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthTokenRep extends JpaRepository<AuthToken,String> {
    Optional<AuthToken >findByUserName(String userName);
}
