package Musaib.MyNetflixProject.Controller;

import Musaib.MyNetflixProject.Dtos.ShowDto;
import Musaib.MyNetflixProject.Services.Impl.ShowServiceImpl;
import Musaib.MyNetflixProject.model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/")
public class ShowController {
    @Autowired
    ShowServiceImpl service;
    @GetMapping("show/{showId}")
    public ResponseEntity<?> showInfo(@PathVariable(value = "showId",required = true) String showId){
        ShowDto showDto=this.service.getShowInfoById(showId);
        return  new ResponseEntity<>(showDto, HttpStatus.OK);
    }
    @GetMapping("show{showName}")
    public  ResponseEntity<?> getShows(@PathVariable(value ="showName",required = true ) String showName){
        Set<Show> shows= this.service.getShowByName(showName);
        return  new ResponseEntity<>(shows,HttpStatus.CREATED);
    }
}
