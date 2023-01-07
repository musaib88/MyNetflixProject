package Musaib.MyNetflixProject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer profileId;
    @Column(unique = true,name = "profileName")
    private  String  name;
    @Column(name = "profileType")
    private  ProfileType type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL)
    Set<WatchedHistory> watchedHistory;
}
