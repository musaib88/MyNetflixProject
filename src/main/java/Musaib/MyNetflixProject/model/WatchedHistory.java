package Musaib.MyNetflixProject.model;

import Musaib.MyNetflixProject.Helper.OtpGenerator;
import lombok.Data;
import lombok.Value;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.awt.geom.GeneralPath;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
@Data
@Entity

public class WatchedHistory {

    @Id
    private String watchedHistoryId;
    private int watchedLength;
    @ManyToOne
    private Profile profile;
    @ManyToOne
    private Video video;
    private Date lastWatched;

}
