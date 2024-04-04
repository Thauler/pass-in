package rocketseat.com.passins.domain.exceptions;

public class EventNotFoundException extends RuntimeException {

  public EventNotFoundException(String message){
    super(message);
  }
}
