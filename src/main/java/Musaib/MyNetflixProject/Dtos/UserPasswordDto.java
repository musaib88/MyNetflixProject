package Musaib.MyNetflixProject.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPasswordDto {
    private  String password;
    private  String email;
    private  String otp;
}
