package Musaib.MyNetflixProject.Services.Impl;

import Musaib.MyNetflixProject.Dtos.SeriesDto;
import Musaib.MyNetflixProject.Exceptions.FieldNotFoundException;
import Musaib.MyNetflixProject.Exceptions.IdNotFoundException;
import Musaib.MyNetflixProject.Repository.AwsRep;
import Musaib.MyNetflixProject.Repository.SeriesRep;
import Musaib.MyNetflixProject.Services.SeriesService;
import Musaib.MyNetflixProject.model.Series;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesServiceImpl implements SeriesService {
    @Autowired
    SeriesRep seriesRep;
    @Autowired
    ModelMapper mapper;
    @Autowired
    AwsRep awsRep;
    @Override
    public SeriesDto getSeriesInfo(String seriesId) {
        Series series=this.seriesRep.findById(seriesId).orElseThrow(()-> new FieldNotFoundException("series Id not found",seriesId));
        SeriesDto seriesDto=this.mapper.map(series, SeriesDto.class);
        String thumbnailLink = awsRep.getPreSignedUrl(series.getThumbnailPath(),60);
         seriesDto.setThumbnailLink(thumbnailLink);
        return seriesDto;
    }
}
