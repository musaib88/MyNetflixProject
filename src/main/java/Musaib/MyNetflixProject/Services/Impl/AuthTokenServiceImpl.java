package Musaib.MyNetflixProject.Services.Impl;

import Musaib.MyNetflixProject.Exceptions.FieldNotFoundException;
import Musaib.MyNetflixProject.Repository.AuthTokenRep;
import Musaib.MyNetflixProject.Security.AuthenticateRequest;
import Musaib.MyNetflixProject.Security.JwtHelper;
import Musaib.MyNetflixProject.Security.MyUserDetailService;
import Musaib.MyNetflixProject.Services.AuthTokenService;
import Musaib.MyNetflixProject.model.AuthToken;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthTokenServiceImpl implements AuthTokenService   {
    @Autowired
    MyUserDetailService myUserDetailService;

   @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtHelper helper;
    @Autowired
    AuthTokenRep rep;
    @Override
    public String createToken(AuthenticateRequest authenticateRequest) throws InvalidCredentialsException {
        try {
            System.out.println(authenticateRequest.getUserName()+" "+authenticateRequest.getPassword());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticateRequest.getUserName(), authenticateRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
        final UserDetails user = myUserDetailService.loadUserByUsername(authenticateRequest.getUserName());
        final String jwtToken = helper.generateToken(user);
        AuthToken token = this.rep.findByUserName(authenticateRequest.getUserName()).orElse(null);
        if(token==null){
            token = new AuthToken();
            token.setTokenId(UUID.randomUUID().toString());
        }
        token.setJwt(jwtToken);
        token.setUserName(user.getUsername());
        this.rep.save(token);
        return jwtToken;

    }

    @Override
    public AuthToken getTokenByUserName(String userName)  {
        AuthToken token=this.rep.findByUserName(userName).orElseThrow(()->new FieldNotFoundException("userName error",userName));
        return token;
    }

    @Override
    public void deleteToken(String userName) {
        AuthToken token=this.rep.findByUserName(userName).orElseThrow(()->new FieldNotFoundException("userName error",userName));
        this.rep.delete(token);
    }


}
