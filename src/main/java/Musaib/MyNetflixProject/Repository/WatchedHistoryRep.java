package Musaib.MyNetflixProject.Repository;

import Musaib.MyNetflixProject.model.Video;
import Musaib.MyNetflixProject.model.WatchedHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WatchedHistoryRep extends JpaRepository<WatchedHistory,Integer> {
    Optional<WatchedHistory> findByVideo(Video video);
    @Query(value="SELECT * FROM watch_history WHERE video_id = :videoId AND profile_id = :profileId",
            nativeQuery = true)
    Optional<WatchedHistory> findByVideoIdProfileId(@Param("videoId") String videoId,
                                                  @Param("profileId") Integer profileId);
}
