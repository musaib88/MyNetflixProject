package Musaib.MyNetflixProject.Services;

import Musaib.MyNetflixProject.Dtos.WatchedHistoryDto;

public interface WatchedHistoryService {
    void maintainWatchHistory(WatchedHistoryDto watchedHistoryDto,String videoId);
    int getWatchHistory(String videoId,Integer profileId);
}
