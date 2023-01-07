package Musaib.MyNetflixProject.Controller;

import Musaib.MyNetflixProject.Repository.UserRep;
import Musaib.MyNetflixProject.Services.Impl.Subscription;
import Musaib.MyNetflixProject.model.Roles;
import Musaib.MyNetflixProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class SubscriptionController {
    @Autowired
    Subscription subscription;
    @PostMapping("/subscription")
    @Secured({Roles.USER,Roles.CUSTOMER})
    public ResponseEntity<?>subscribeUser(){
       String message= this.subscription.SubscribeUser();
       return  ResponseEntity.ok(message);
    }
    @DeleteMapping("/subscription")
    @Secured(Roles.CUSTOMER)
    public  ResponseEntity<?>unsubscribeUser(){
        String message=this.subscription.UnSubscribeUser();
        return ResponseEntity.ok(message);
    }

}
