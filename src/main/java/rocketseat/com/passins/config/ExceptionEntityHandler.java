package rocketseat.com.passins.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rocketseat.com.passins.domain.attendee.exceptions.AttendeeAlreadyExistsException;
import rocketseat.com.passins.domain.attendee.exceptions.AttendeeNotFoundException;
import rocketseat.com.passins.domain.attendee.exceptions.EventFullException;
import rocketseat.com.passins.domain.checkin.exceptions.CheckInAlreadyExists;
import rocketseat.com.passins.domain.event.exceptions.EventNotFoundException;
import rocketseat.com.passins.dto.general.ErrorResponseDTO;

@ControllerAdvice
public class ExceptionEntityHandler {
  @ExceptionHandler(EventNotFoundException.class)
  public ResponseEntity handlerEventNotFound(EventNotFoundException exception){
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(EventFullException.class)
  public ResponseEntity<ErrorResponseDTO> handlerEventFull(EventFullException exception){
    return ResponseEntity.badRequest().body(new ErrorResponseDTO(exception.getMessage()));
  }

  @ExceptionHandler(AttendeeNotFoundException.class)
  public ResponseEntity handlerAttendeeNotFound(AttendeeNotFoundException exception){
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(AttendeeAlreadyExistsException.class)
  public ResponseEntity handlerAttendeeAlreadyExists(AttendeeAlreadyExistsException exception){
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
  }

  @ExceptionHandler(CheckInAlreadyExists.class)
  public ResponseEntity handlerCheckInAlreadyExists(CheckInAlreadyExists exception){
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
  }
}
