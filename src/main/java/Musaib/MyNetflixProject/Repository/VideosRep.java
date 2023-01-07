package Musaib.MyNetflixProject.Repository;

import Musaib.MyNetflixProject.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideosRep  extends JpaRepository<Video,String> {
}
