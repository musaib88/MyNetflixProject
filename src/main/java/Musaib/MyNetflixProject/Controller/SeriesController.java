package Musaib.MyNetflixProject.Controller;

import Musaib.MyNetflixProject.Dtos.SeriesDto;
import Musaib.MyNetflixProject.Services.Impl.SeriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SeriesController {
    @Autowired
    SeriesServiceImpl service;
    @GetMapping("/series/{seriesId}")
    public ResponseEntity<?> seriesInfo(@PathVariable(value = "seriesId",required = true) String seriesId){
        SeriesDto seriesDto=this.service.getSeriesInfo(seriesId);
        return new ResponseEntity<>(seriesDto, HttpStatus.CREATED);
    }

}
