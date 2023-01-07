package Musaib.MyNetflixProject.Repository;

import Musaib.MyNetflixProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRep extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String username);
    Optional<User> findByPhoneNumber(String phoneNumber);

}
