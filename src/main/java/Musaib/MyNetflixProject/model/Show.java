package Musaib.MyNetflixProject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "shows")
public class Show {
    @Id
    @Column(name = "show_id", updatable = false, nullable = false)
    private String showId;
    private String name;
    private String typeOfShow;

    private String genre;

    private String audience;
    private double rating;
    private int length;
    private String thumbnailPath;
}
