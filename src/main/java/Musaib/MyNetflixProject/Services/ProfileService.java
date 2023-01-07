package Musaib.MyNetflixProject.Services;

import Musaib.MyNetflixProject.Dtos.ProfileDto;
import Musaib.MyNetflixProject.model.Profile;

public interface ProfileService {
    String addProfile(ProfileDto profileDto);
    String deleteProfile(Integer profileId);
}
