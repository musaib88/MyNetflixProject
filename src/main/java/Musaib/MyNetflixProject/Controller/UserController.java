package Musaib.MyNetflixProject.Controller;

import Musaib.MyNetflixProject.Dtos.UserDto;
import Musaib.MyNetflixProject.Dtos.UserPasswordDto;
import Musaib.MyNetflixProject.Services.Impl.UserServiceImpl;
import Musaib.MyNetflixProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody  UserDto userDto){
        String message=this.userService.createUser(userDto);
        return  ResponseEntity.ok(message);
    }
    @PostMapping("/user/reset/password")
    public ResponseEntity<?> resetPassword(@RequestParam("Email") String email){
        String message=this.userService.resetPassword(email);
      return ResponseEntity.ok(message);
    }
    @PostMapping("/user/password")
    public ResponseEntity<?> updateUserPassword(@RequestBody UserPasswordDto userPasswordDto)  {
        String message=this.userService.updateUserPassword(userPasswordDto);
        return  new ResponseEntity<>(message, HttpStatus.CREATED);

    }
    @PostMapping("logout")
    public ResponseEntity<?> logoutUser(){

   return ResponseEntity.ok("logout Successfully");
    }
}
