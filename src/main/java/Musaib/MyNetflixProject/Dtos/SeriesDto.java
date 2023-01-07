package Musaib.MyNetflixProject.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeriesDto {
    private String seriesId;
    private String name;
    private int numberOfVideos;
    private double rating;
    private int totalLength;
    private  String thumbnailLink;
}
