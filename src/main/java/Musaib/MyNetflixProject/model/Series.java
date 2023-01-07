package Musaib.MyNetflixProject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
@Data
@Entity
public class Series {
    @Id
    @Column(name = "series_id", updatable = false, nullable = false)
    private String seriesId;
    private String name;
    private int numberOfVideos;
    private double rating;
    private int totalLength;
    private String thumbnailPath;
    @OneToMany(mappedBy = "series")
    private Set<Video> videos;
    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;
}
