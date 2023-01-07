package Musaib.MyNetflixProject.Dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service

public class EmailDetails {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;


}
