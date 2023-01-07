package Musaib.MyNetflixProject.Services.Impl;

import Musaib.MyNetflixProject.Dtos.ShowDto;
import Musaib.MyNetflixProject.Exceptions.FieldNotFoundException;
import Musaib.MyNetflixProject.Exceptions.IdNotFoundException;
import Musaib.MyNetflixProject.Repository.AwsRep;
import Musaib.MyNetflixProject.Repository.ShowRep;
import Musaib.MyNetflixProject.Services.ShowService;
import Musaib.MyNetflixProject.model.Show;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    AwsRep awsRep;
    @Autowired
    ShowRep showRep;
    @Autowired
    ModelMapper mapper;
    @Override
    public ShowDto getShowInfoById(String showId) {
        Show show= this.showRep.findById(showId).orElseThrow(()-> new FieldNotFoundException(" show id not found ",showId));
        ShowDto showDto=this.mapper.map(show,ShowDto.class);
        String thumbnailLink = awsRep.getPreSignedUrl(show.getThumbnailPath(),60);
        showDto.setThumbnailLink(thumbnailLink);
        return showDto;
    }

    @Override
    public Set<Show> getShowByName(String showName) {
        Set<Show> shows=this.showRep.findByShowName(showName);

        return shows;
    }
}
