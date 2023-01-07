package Musaib.MyNetflixProject.Services;

import Musaib.MyNetflixProject.Dtos.ShowDto;
import Musaib.MyNetflixProject.model.Show;

import java.util.Set;

public interface ShowService {
      ShowDto getShowInfoById(String showId);
      Set<Show>  getShowByName(String showName);



}
