package Musaib.MyNetflixProject.Controller;

import Musaib.MyNetflixProject.Security.AuthenticateRequest;
import Musaib.MyNetflixProject.Security.AuthenticateResponse;
import Musaib.MyNetflixProject.Services.Impl.AuthTokenServiceImpl;
import Musaib.MyNetflixProject.model.User;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    AuthTokenServiceImpl service;
    @PostMapping(path = "/login")
    public ResponseEntity<?>validateUser(@RequestBody AuthenticateRequest authenticateRequest) throws InvalidCredentialsException {
        String jwt = this.service.createToken(authenticateRequest);
        return  new ResponseEntity<>(new AuthenticateResponse(jwt), HttpStatus.CREATED);
    }
    @PostMapping("/user/logout")
    public ResponseEntity<?>logoutUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user=(User) authentication.getPrincipal();
        String userName=user.getEmail();
        this.service.deleteToken(userName);
        return new ResponseEntity<>("logout sucessfully",HttpStatus.OK);
    }

}
