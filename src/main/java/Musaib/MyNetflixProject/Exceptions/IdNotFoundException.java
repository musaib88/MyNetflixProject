package Musaib.MyNetflixProject.Exceptions;

import lombok.NoArgsConstructor;


public class IdNotFoundException extends RuntimeException {
    private  Integer id;

    public IdNotFoundException(String message, Integer id) {
        super(message);
        this.id = id;
    }
}
