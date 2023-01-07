package Musaib.MyNetflixProject.Services.Impl;

import Musaib.MyNetflixProject.Exceptions.FieldNotFoundException;
import Musaib.MyNetflixProject.Exceptions.IdNotFoundException;
import Musaib.MyNetflixProject.Repository.AwsRep;
import Musaib.MyNetflixProject.Repository.VideosRep;
import Musaib.MyNetflixProject.Services.VideoService;
import Musaib.MyNetflixProject.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideosRep videosRep;
    @Autowired
    AwsRep awsRep;
    @Override
    public void rateVideo(String videoId,Double videoRating) {
        Video video=this.videosRep.findById(videoId).orElseThrow(()-> new FieldNotFoundException("video not found ",videoId));
         video.setNoOfResponses(video.getNoOfResponses()+1);
        video.setRating((video.getRating()+videoRating)/video.getNoOfResponses());
        this.videosRep.save(video);
    }

    @Override
    public String getVideoUrl(String videoId) {
        Video video=this.videosRep.findById(videoId).orElseThrow(()-> new FieldNotFoundException("video not found by Id",videoId));
        String path=video.getVideoPath();
         String url=this.awsRep.getPreSignedUrl(path,120);
        return url;
    }

    @Override
    public String getThumbNailUrl(String videoId) {
        Video video=this.videosRep.findById(videoId).orElseThrow(()-> new FieldNotFoundException("video not found by Id",videoId));
        String path=video.getThumbnailPath();
        String url=this.awsRep.getPreSignedUrl(path,120);
        return url;
    }
}
