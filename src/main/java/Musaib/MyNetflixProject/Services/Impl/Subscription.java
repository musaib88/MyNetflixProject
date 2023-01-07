package Musaib.MyNetflixProject.Services.Impl;

import Musaib.MyNetflixProject.Exceptions.IdNotFoundException;
import Musaib.MyNetflixProject.Repository.UserRep;
import Musaib.MyNetflixProject.model.Roles;
import Musaib.MyNetflixProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class Subscription {
    @Autowired
    UserRep userRep;
    public String SubscribeUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user=(User)authentication.getPrincipal();
        if(user.getSubscription()==true){
            return "you are already subscribed ";
        }
        user.setSubscription(true);
        user.setRoles(Roles.CUSTOMER);
        this.userRep.save(user);
        if(user.getSubscription()==true){
            return "Subscription activated successfully";
        }
        return null;
    }
    public String UnSubscribeUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user=(User)authentication.getPrincipal();
        user.setSubscription(false);
        user.setRoles(Roles.USER);
        this.userRep.save(user);
        if(user.getSubscription()==false){
            return "Subscription cancelled successfully";
        }
        return null;
    }
}
