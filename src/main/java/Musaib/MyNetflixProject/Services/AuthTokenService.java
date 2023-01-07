package Musaib.MyNetflixProject.Services;

import Musaib.MyNetflixProject.Security.AuthenticateRequest;
import Musaib.MyNetflixProject.model.AuthToken;
import org.apache.http.auth.InvalidCredentialsException;

public interface AuthTokenService {
    String createToken(AuthenticateRequest authenticateRequest) throws InvalidCredentialsException;
    AuthToken getTokenByUserName(String userName);
    void deleteToken(String userName);
}
