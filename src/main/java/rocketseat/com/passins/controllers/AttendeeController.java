package rocketseat.com.passins.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passins.dto.attendee.AttendeeBadgeResponseDTO;
import rocketseat.com.passins.services.AttendeeService;
import rocketseat.com.passins.services.CheckInService;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {
  private final AttendeeService attendeeService;
  private final CheckInService checkInService;

  @GetMapping("/{attendeeId}/badge")
  public ResponseEntity<AttendeeBadgeResponseDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder){
    AttendeeBadgeResponseDTO response = this.attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/{attendeeId}/check-in")
  public ResponseEntity registerCheckIn(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder){
    this.attendeeService.checkInAttendee(attendeeId);

    var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();

    return ResponseEntity.created(uri).build();
  }
}
