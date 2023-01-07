package Musaib.MyNetflixProject.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShowDto {
    private String showId;
    private String name;
    private String typeOfShow;

    private String genre;

    private String audience;
    private double rating;
    private int length;
    private String thumbnailLink;


}
