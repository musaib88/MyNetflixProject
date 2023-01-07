package Musaib.MyNetflixProject.Dtos;

import Musaib.MyNetflixProject.model.ProfileType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDto {
    private  String  name;
    private ProfileType type;
}
