package Musaib.MyNetflixProject.Services.Impl;

import Musaib.MyNetflixProject.Dtos.ProfileDto;
import Musaib.MyNetflixProject.Exceptions.FieldNotFoundException;
import Musaib.MyNetflixProject.Exceptions.IdNotFoundException;
import Musaib.MyNetflixProject.Repository.ProfileRep;
import Musaib.MyNetflixProject.Repository.UserRep;
import Musaib.MyNetflixProject.Services.ProfileService;
import Musaib.MyNetflixProject.model.Profile;
import Musaib.MyNetflixProject.model.ProfileType;
import Musaib.MyNetflixProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    UserRep userRep;
    @Autowired
    ProfileRep profileRep;

    @Override
    public String addProfile(ProfileDto profileDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Profile profile=new Profile();
        profile.setType(ProfileType.GENERAL);
        profile.setUser(user);
        profileRep.save(profile);
        return "Profile Added successfully";
    }

    @Override
    public String deleteProfile(Integer profileId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Profile profile=this.profileRep.findById(profileId).orElseThrow(()-> new IdNotFoundException("profile not found ",profileId));
            if(profile.getUser().getId().equals(user.getId())){
                profileRep.delete(profile);
                return " Profile Deleted Successfully";
            }
        return  " something went Wrong";
    }
}
