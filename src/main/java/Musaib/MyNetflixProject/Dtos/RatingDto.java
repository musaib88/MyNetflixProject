package Musaib.MyNetflixProject.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RatingDto {
    @Size(min =0,max=10,message = "rating must be between 0 and 10")
    private  double rating;
}
