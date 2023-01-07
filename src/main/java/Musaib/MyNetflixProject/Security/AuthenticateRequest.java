package Musaib.MyNetflixProject.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticateRequest {
    String userName;
    String password;
}
