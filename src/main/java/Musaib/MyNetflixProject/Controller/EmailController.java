package Musaib.MyNetflixProject.Controller;

import Musaib.MyNetflixProject.Dtos.EmailDetails;
import Musaib.MyNetflixProject.Dtos.EmailVerifyDto;
import Musaib.MyNetflixProject.Exceptions.FieldNotFoundException;
import Musaib.MyNetflixProject.Helper.OtpGenerator;
import Musaib.MyNetflixProject.Repository.UserRep;
import Musaib.MyNetflixProject.Services.Impl.EmailSenderServiceImpl;
import Musaib.MyNetflixProject.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/user")
public class EmailController {
    @Autowired
    UserRep userRep;
    @Autowired
    EmailSenderServiceImpl service;
    @Autowired
    OtpGenerator otpGenerator;
    @GetMapping("/email/otp")
    public ResponseEntity<?> sendOtp(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String email=user.getEmail();
        if(user.getEmailVerification()==true){
            String tok="email verified already";
            return (ResponseEntity<?>) ResponseEntity.ok(tok);}
        EmailDetails emailDetails=new EmailDetails();
        emailDetails.setRecipient(email);
        emailDetails.setSubject("Email verification");
        emailDetails.setMsgBody("your otp for verification is "+ otpGenerator.getRandomNumberString()  + "  valid for only 5 minutes" );
        String message=this.service.sendSimpleMail(emailDetails);
        return  ResponseEntity.ok(message);
    }
    @PostMapping("/email")
    public ResponseEntity<?> verifyEmailOtp(@RequestBody EmailVerifyDto emailVerifyDto){
        String otp=emailVerifyDto.getOtp();
        String email=emailVerifyDto.getEmail();
        String message=service.verifyOtp(otp,email);
        return  ResponseEntity.ok(message);
    }
}
