package Musaib.MyNetflixProject.Services.Impl;

import Musaib.MyNetflixProject.Dtos.EmailDetails;
import Musaib.MyNetflixProject.Dtos.UserDto;
import Musaib.MyNetflixProject.Dtos.UserPasswordDto;
import Musaib.MyNetflixProject.Exceptions.FieldNotFoundException;
import Musaib.MyNetflixProject.Helper.OtpGenerator;
import Musaib.MyNetflixProject.Repository.UserRep;
import Musaib.MyNetflixProject.Services.UserService;
import Musaib.MyNetflixProject.model.AccountStatus;
import Musaib.MyNetflixProject.model.Roles;
import Musaib.MyNetflixProject.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    OtpGenerator otpGenerator;
    @Autowired
    UserRep userRep;
    @Autowired
    ModelMapper mapper;
    @Override
    public String createUser(UserDto userDto) {
        User user= this.mapper.map(userDto,User.class);


         user.setRoles(Roles.USER);
         user.setSubscription(false);
         user.setEmailVerification(false);
         user.setPhoneNumberVerification(false);
         user.setAccountStatus(AccountStatus.ACTIVE);
         this.userRep.save(user);

        if(user!=null){
            return "User created successfully";
        }
        return null;
    }

    @Override
    public String updateUserPassword(UserPasswordDto userPasswordDto) {
        String email = userPasswordDto.getEmail();
        String newPassword = userPasswordDto.getPassword();
        String otp = userPasswordDto.getOtp();
        User user = this.userRep.findByEmail(email).orElseThrow(() -> new FieldNotFoundException("given email not found", email));
        Long validity = user.getOtpValidity();
        String dataOtp = user.getUserOtpGenerated();
        System.out.println(validity);
        System.out.println(System.currentTimeMillis());
        System.out.println(otp);
        System.out.println(dataOtp);
        if (validity < System.currentTimeMillis()) {
            otp = "0";
        }
        if (otp.equals(dataOtp)) {
            user.setPassword(newPassword);
            user.setUserOtpGenerated(null);
            user.setOtpValidity(null);
            userRep.save(user);
            return "Password reset successfully ";
        }
        return "invalid otp  or email";
    }

    @Value("${spring.mail.username}") private String sender;
    @Override
    public String resetPassword(String email) {
        User user=this.userRep.findByEmail(email).orElseThrow(()->new FieldNotFoundException("given email not found ",email));
        SimpleMailMessage mailMessage
                = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(email);
        mailMessage.setText("otp for password rest is " + otpGenerator.getRandomNumberString() +"  and is valid for 5 minutes only " );
        mailMessage.setSubject(" otp for password reset");
        javaMailSender.send(mailMessage);
        user.setUserOtpGenerated(otpGenerator.getOtpSent());
        user.setOtpValidity(otpGenerator.getOtpExpireTime());
        userRep.save(user);

        return "otp sent successfully for password Reset";
    }
}
