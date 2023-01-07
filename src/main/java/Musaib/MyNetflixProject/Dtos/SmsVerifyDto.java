package Musaib.MyNetflixProject.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SmsVerifyDto {
    private  String phoneNumber;
    private  String otp;
}
