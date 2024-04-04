package rocketseat.com.passins.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import rocketseat.com.passins.domain.exceptions.EventNotFoundException;

@ControllerAdvice
public class ExceptionEntityHandler {
  public ResponseEntity handlerEventNotFound(EventNotFoundException exception){
    return ResponseEntity.notFound().build();
  }
}
