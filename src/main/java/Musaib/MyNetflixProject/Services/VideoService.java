package Musaib.MyNetflixProject.Services;

public interface VideoService {
    void rateVideo(String videoId,Double videoRating);
    String getVideoUrl(String videoId);
    String getThumbNailUrl(String videoId);
}
