package Musaib.MyNetflixProject.Services.Impl;

import Musaib.MyNetflixProject.Dtos.EmailDetails;

import Musaib.MyNetflixProject.Exceptions.FieldNotFoundException;
import Musaib.MyNetflixProject.Helper.OtpGenerator;
import Musaib.MyNetflixProject.Repository.UserRep;

import Musaib.MyNetflixProject.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmailSenderServiceImpl  {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    UserRep userRep;
    @Autowired
    OtpGenerator otpGenerator;

    @Value("${spring.mail.username}") private String sender;


    public String sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
             String userEmail=details.getRecipient();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            User user=this.userRep.findByEmail(userEmail).orElseThrow(()->new FieldNotFoundException("email not found",userEmail));
            user.setOtpValidity(otpGenerator.getOtpExpireTime());
            user.setUserOtpGenerated(otpGenerator.getOtpSent());
            this.userRep.save(user);

            return "Otp  Sent  to your email Successfully...";


        }
        catch (Exception e) {
            return "Error while Sending Mail";
        }

    }

    public  String verifyOtp(String otp,String email){
       User user=this.userRep.findByEmail(email).orElseThrow(()->new FieldNotFoundException("user email not matching",email));

        if(user.getEmailVerification()==true)
            return "email verified already";
       String  usersOtp=user.getUserOtpGenerated();
       Long expire=user.getOtpValidity();
        System.out.println(expire);
        System.out.println(System.currentTimeMillis());

        if (expire<System.currentTimeMillis()){
            usersOtp="0";
        }
        if(otp.equals(usersOtp)){
            user.setEmailVerification(true);
           user.setUserOtpGenerated(null);
           user.setOtpValidity(null);
            this.userRep.save(user);


            return  "Email verified successfully";
        }
        else
            return "OTP expired or not matching ";

    }
}
