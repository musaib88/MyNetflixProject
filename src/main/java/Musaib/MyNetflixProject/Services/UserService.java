package Musaib.MyNetflixProject.Services;

import Musaib.MyNetflixProject.Dtos.UserDto;
import Musaib.MyNetflixProject.Dtos.UserPasswordDto;

public interface UserService {
    String createUser(UserDto userDto);
    String updateUserPassword(UserPasswordDto userPasswordDto);
    String resetPassword(String email);


}
