package Musaib.MyNetflixProject.Services.Impl;

import Musaib.MyNetflixProject.Exceptions.FieldNotFoundException;
import Musaib.MyNetflixProject.Helper.OtpGenerator;
import Musaib.MyNetflixProject.Repository.UserRep;
import Musaib.MyNetflixProject.model.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class SmsSenderServiceImpl {

    @Autowired
    UserRep userRep;
    @Autowired
    OtpGenerator otpGenerator;
    public String sendOtp(String phoneNumber){


           User user=this.userRep.findByPhoneNumber(phoneNumber).orElseThrow(()->new FieldNotFoundException("user not found with the phoneNumber",phoneNumber));
        if(user.getPhoneNumberVerification()==true)
            return "phoneNumber verified already";
         try { String otp=otpGenerator.getRandomNumberString();
        user.setUserOtpGenerated(otpGenerator.getOtpSent());
        user.setOtpValidity(otpGenerator.getOtpExpireTime());
        userRep.save(user);
        Twilio.init("AuthId","AuthToken" );
        Message message = Message.creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber("your twilo phoneNo"),"your otp is " + otp +"  valid for 5 minutes"
                        )
                .create();}
        catch (BadCredentialsException e){
        return " invalid phoneNumber or something went wrong";
        }
        return " otp sent sucessfully";
}

public String verifyOtp(String phoneNumber,String otp){
        User user=this.userRep.findByPhoneNumber(phoneNumber).orElseThrow(()->new FieldNotFoundException("user not found with the phoneNumber",phoneNumber));
       if(user.getPhoneNumberVerification()==true)
           return "phoneNumber verified already";
    String dataOtp=user.getUserOtpGenerated();
    Long validity=user.getOtpValidity();
    try {if (validity<System.currentTimeMillis()){
        dataOtp="0";
    }
    if(dataOtp.equals(otp)){
      user.setPhoneNumberVerification(true);
      user.setUserOtpGenerated(null);
      user.setOtpValidity(null);
      userRep.save(user);
      return " phoneNumber verified Successfully";

    }

   }
   catch (BadCredentialsException e){
       return  " invalid otp or phoneNumber";
   }
   return "something wrong" ;
}



}
