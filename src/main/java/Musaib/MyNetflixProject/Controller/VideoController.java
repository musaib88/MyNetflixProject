package Musaib.MyNetflixProject.Controller;

import Musaib.MyNetflixProject.Dtos.RatingDto;
import Musaib.MyNetflixProject.Services.Impl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class VideoController {
    @Autowired
    VideoServiceImpl videoService;
    @GetMapping("video/{videoId}/link")
    public ResponseEntity<?> getVideoUrl(@PathVariable(value = "videoId",required = true) String videoId){
        String url=this.videoService.getVideoUrl(videoId);
        return  new ResponseEntity<>(url, HttpStatus.CREATED);
    }
    @GetMapping("video/{videoId}/rating")
    public  ResponseEntity<?> setVideoRating(@PathVariable(value = "videoId",required = true) String videoId,
                                             @RequestBody RatingDto ratingDto){

        Double rating=ratingDto.getRating();
        this.videoService.rateVideo(videoId,rating);
        return  ResponseEntity.ok("thumbsUp");

    }
    @GetMapping("video/{videoId}/thumbnail/link")
    public  ResponseEntity<?> getThumbnailLink(@PathVariable(value = "videoId",required = true) String videoId){
        String thumburl=this.videoService.getThumbNailUrl(videoId);
        return  new ResponseEntity<>(thumburl,HttpStatus.CREATED);
    }
}
