package Musaib.MyNetflixProject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "videos")
public class Video {
    @Id
    @Column(name="video_id",updatable = false, nullable = false)
    private String videoId;
    private String name;
    private int noOfResponses;
    private String videoPath;
    private String thumbnailPath;
    private double rating;
    private int videoLength;
    private Date releaseDate;
    @ManyToOne
    Show show;
    @ManyToOne
    Series series;
    @OneToMany(mappedBy = "video")
    Set<WatchedHistory> watchedHistory;

}
