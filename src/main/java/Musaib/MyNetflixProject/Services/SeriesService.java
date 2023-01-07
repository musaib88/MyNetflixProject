package Musaib.MyNetflixProject.Services;

import Musaib.MyNetflixProject.Dtos.SeriesDto;

public interface SeriesService {
    SeriesDto getSeriesInfo(String seriesId);
}
