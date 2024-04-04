package rocketseat.com.passins.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passins.domain.attendee.Attendee;
import rocketseat.com.passins.domain.checkin.CheckIn;
import rocketseat.com.passins.dto.attendee.AttendeeDetails;
import rocketseat.com.passins.dto.attendee.AttendeeListResponseDTO;
import rocketseat.com.passins.repositories.AttendeeRepository;
import rocketseat.com.passins.repositories.CheckInRepository;

@Service
@RequiredArgsConstructor
public class AttendeeService {
  private final AttendeeRepository attendeeRepository;
  private final CheckInRepository checkInRepository;

  public List<Attendee> getAllAttendeesFromEvent(String eventId){
    return this.attendeeRepository.findByEventId(eventId);
  }

  public AttendeeListResponseDTO getEventsAttendee(String eventId){
    List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

    List<AttendeeDetails> attendeeDetailsList = attendeeList.stream()
        .map(attendee -> {
          Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());
          LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);
          return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
        }).toList();

    return new AttendeeListResponseDTO(attendeeDetailsList);
  }
}
