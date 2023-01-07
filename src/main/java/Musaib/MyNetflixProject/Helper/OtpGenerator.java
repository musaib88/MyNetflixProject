package Musaib.MyNetflixProject.Helper;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
@Data
@NoArgsConstructor
@Service
public class OtpGenerator {
    private  String otpSent;
    private Long otpExpireTime;
    public  String getRandomNumberString(){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        String otp= String.format("%06d", number);
        otpSent =otp;
        otpExpireTime=System.currentTimeMillis()+(5*60000);
        return otp;
    }

}
