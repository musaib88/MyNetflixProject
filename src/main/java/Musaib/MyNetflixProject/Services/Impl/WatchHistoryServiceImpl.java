package Musaib.MyNetflixProject.Services.Impl;

import Musaib.MyNetflixProject.Dtos.WatchedHistoryDto;
import Musaib.MyNetflixProject.Exceptions.FieldNotFoundException;
import Musaib.MyNetflixProject.Repository.ProfileRep;
import Musaib.MyNetflixProject.Repository.VideosRep;
import Musaib.MyNetflixProject.Repository.WatchedHistoryRep;
import Musaib.MyNetflixProject.Services.WatchedHistoryService;
import Musaib.MyNetflixProject.model.Profile;
import Musaib.MyNetflixProject.model.User;
import Musaib.MyNetflixProject.model.Video;
import Musaib.MyNetflixProject.model.WatchedHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
@Service
public class WatchHistoryServiceImpl implements WatchedHistoryService {
    @Autowired
    VideosRep videosRep;
    @Autowired
    ProfileRep profileRep;
    @Autowired
    WatchedHistoryRep watchedHistoryRep;
    @Override
    public void maintainWatchHistory(WatchedHistoryDto watchedHistoryDto, String videoId) {
        Video video=this.videosRep.findById(videoId).orElseThrow(()-> new FieldNotFoundException("videoId not found ",videoId));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Integer profileId=watchedHistoryDto.getProfileId();
        Profile profile=this.profileRep.findById(profileId).orElseThrow(()->new FieldNotFoundException("profile not found ",profileId+""));
        User user1=profile.getUser();
        if(user1.getId()==(user.getId())){
            if(watchedHistoryDto.getWatchedLength()>=0 && watchedHistoryDto.getWatchedLength()<=video.getVideoLength()){
               WatchedHistory watchedHistory= this.watchedHistoryRep.findByVideoIdProfileId(videoId,profileId).orElse(null);
                if(watchedHistory==null){
                    watchedHistory = new WatchedHistory();
                    watchedHistory.setLastWatched(new Date());
                    String uuid = String.valueOf(UUID.randomUUID());
                    watchedHistory.setWatchedHistoryId(uuid);
                    watchedHistory.setWatchedLength(watchedHistoryDto.getWatchedLength());
                    this.watchedHistoryRep.save(watchedHistory);
                }
                else{
                    watchedHistory.setLastWatched(new Date());
                    watchedHistory.setWatchedLength(watchedHistoryDto.getWatchedLength());
                    this.watchedHistoryRep.save(watchedHistory);


                }
            }
        }

    }

    @Override
    public int getWatchHistory(String videoId, Integer profileId) {
        Video video=this.videosRep.findById(videoId).orElseThrow(()-> new FieldNotFoundException("videoId not found ",videoId));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Profile profile=this.profileRep.findById(profileId).orElseThrow(()->new FieldNotFoundException("profile not found",profileId+""));
        User user1=profile.getUser();
        if(user.getId()==user1.getId()){
            WatchedHistory watchedHistory=this.watchedHistoryRep.findByVideoIdProfileId(videoId,profileId).orElseThrow(()-> new FieldNotFoundException(" watchHistory not found ","related to given ids"));
            int watchLength=watchedHistory.getWatchedLength();
            return watchLength;
        }


        return 0;
    }
}
