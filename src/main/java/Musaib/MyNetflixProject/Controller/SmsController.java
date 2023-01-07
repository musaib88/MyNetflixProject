package Musaib.MyNetflixProject.Controller;

import Musaib.MyNetflixProject.Dtos.SmsVerifyDto;
import Musaib.MyNetflixProject.Services.Impl.SmsSenderServiceImpl;
import Musaib.MyNetflixProject.model.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SmsController {
    @Autowired
    SmsSenderServiceImpl service;
    @PostMapping(value = "phoneNumber/otp")
    public ResponseEntity<String> sendOtp() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String phoneNumber=user.getPhoneNumber();
        String message=this.service.sendOtp(phoneNumber);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @PostMapping(value ="phoneNumber")
    public  ResponseEntity<?> verifyOtp(@RequestBody SmsVerifyDto smsVerifyDto){
        String phoneNumber=smsVerifyDto.getPhoneNumber();
        String otp=smsVerifyDto.getOtp();
        String mess=this.service.verifyOtp(phoneNumber,otp);
        return new ResponseEntity<String>(mess, HttpStatus.OK);
    }

}
