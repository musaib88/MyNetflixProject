package Musaib.MyNetflixProject.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    @NotEmpty
    @Size(min =4,max =20,message ="name must be min of four of size and max 20")
    private  String name;
    @Email
    @NotEmpty(message = "Email is missing")
    private String email;
    @NotEmpty(message = "phoneNO. is missing")
    @Size(min =10,max =13,message ="phone number must be atleast of  ten digits")
    private String phoneNumber;
    @Pattern(regexp = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$",message ="password must contain one Capital,small,one digit and a Symbol " )

    private  String password;
}
