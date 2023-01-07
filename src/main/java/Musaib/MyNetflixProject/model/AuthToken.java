package Musaib.MyNetflixProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {
    @Id
    private String TokenId;
    private String userName;
    private String jwt;
}
