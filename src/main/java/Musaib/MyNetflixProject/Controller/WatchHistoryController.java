package Musaib.MyNetflixProject.Controller;

import Musaib.MyNetflixProject.Dtos.WatchedHistoryDto;
import Musaib.MyNetflixProject.Services.Impl.WatchHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class WatchHistoryController {
    @Autowired
    WatchHistoryServiceImpl service;
    @PostMapping("/video/{videoId}/watchTime")
    public ResponseEntity<?> maintainWatchTime(@RequestBody  WatchedHistoryDto watchedHistoryDto,@PathVariable("videoId") String videoId){
      this.service.maintainWatchHistory(watchedHistoryDto,videoId);
      return  ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @GetMapping("/video/{videoId}/watchTime/{profileId}")
    public  ResponseEntity<?> getWatchTime(@PathVariable("videoId") String videoId,@PathVariable("profileId") Integer profileId ){
        int watchTime=this.service.getWatchHistory(videoId,profileId);
        return  new ResponseEntity<>(watchTime,HttpStatus.ACCEPTED);
    }
}
