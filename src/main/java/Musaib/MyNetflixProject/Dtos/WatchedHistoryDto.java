package Musaib.MyNetflixProject.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WatchedHistoryDto {
    private int watchedLength;
    private  Integer profileId;

}
