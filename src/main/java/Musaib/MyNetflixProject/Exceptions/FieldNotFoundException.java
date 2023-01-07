package Musaib.MyNetflixProject.Exceptions;

public class FieldNotFoundException extends RuntimeException{
    String userName;

    public FieldNotFoundException(String message, String userName) {
        super(message);
        this.userName = userName;
    }
}
