package rocketseat.com.passins.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passins.dto.attendee.AttendeeListResponseDTO;
import rocketseat.com.passins.dto.event.EventIdDTO;
import rocketseat.com.passins.dto.event.EventRequestDTO;
import rocketseat.com.passins.dto.event.EventResponseDTO;
import rocketseat.com.passins.services.AttendeeService;
import rocketseat.com.passins.services.EventService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
  private final EventService eventService;
  private final AttendeeService attendeeService;

  @GetMapping("/{id}")
  public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
    EventResponseDTO event = this.eventService.getEventDetail(id);
    return ResponseEntity.ok().body(event);
  }

  @PostMapping
  public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
    EventIdDTO eventIdDTO = this.eventService.createEvent(body);

    var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

    return ResponseEntity.created(uri).body(eventIdDTO);
  }

  @GetMapping("/attendees/{id}")
  public ResponseEntity<AttendeeListResponseDTO> getEventAttendees(@PathVariable String id){
    AttendeeListResponseDTO attendeeListResponse = this.attendeeService.getEventsAttendee(id);
    return ResponseEntity.ok().body(attendeeListResponse);
  }
}
