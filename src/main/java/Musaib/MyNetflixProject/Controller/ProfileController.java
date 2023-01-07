package Musaib.MyNetflixProject.Controller;

import Musaib.MyNetflixProject.Dtos.ProfileDto;
import Musaib.MyNetflixProject.Services.Impl.ProfileServiceImpl;
import Musaib.MyNetflixProject.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/user")
public class ProfileController {
    @Autowired
    ProfileServiceImpl profileService;
    @PostMapping("/profile")
    @Secured(Roles.CUSTOMER)

    public ResponseEntity<?>addProfile(@RequestBody ProfileDto profileDto){
        String message=this.profileService.addProfile(profileDto);
        return ResponseEntity.ok(message);

    }
    @DeleteMapping("/profile/{profileId}")
    @Secured(Roles.CUSTOMER)
    public  ResponseEntity<?> deleteProfile(@PathVariable(value = "profileId",required = true)  Integer profileId){
        String message=this.profileService.deleteProfile(profileId);
        return ResponseEntity.ok(message);
    }

}
