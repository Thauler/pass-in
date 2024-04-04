package rocketseat.com.passins.services;

import java.text.Normalizer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passins.domain.attendee.Attendee;
import rocketseat.com.passins.domain.event.Event;
import rocketseat.com.passins.domain.exceptions.EventNotFoundException;
import rocketseat.com.passins.dto.event.EventIdDTO;
import rocketseat.com.passins.dto.event.EventRequestDTO;
import rocketseat.com.passins.dto.event.EventResponseDTO;
import rocketseat.com.passins.repositories.EventRepository;

@Service
@RequiredArgsConstructor
public class EventService {
  private final EventRepository eventRepository;
  private final AttendeeService attendeeService;

  public EventResponseDTO getEventDetail(String eventId){
    Event event = this.eventRepository
        .findById(eventId)
        .orElseThrow(() -> new EventNotFoundException("Event not found with this ID: " + eventId));
    List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);
    return new EventResponseDTO(event, attendeeList.size());
  }

  public EventIdDTO createEvent(EventRequestDTO eventDTO){
    Event newEvent = new Event();

    newEvent.setTitle(eventDTO.title());
    newEvent.setDetails(eventDTO.details());
    newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
    newEvent.setSlug(this.createSlug(eventDTO.title()));

    this.eventRepository.save(newEvent);

    return new EventIdDTO(newEvent.getId());
  }

  private String createSlug(String text){
    String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
    return normalized
        .replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
        .replaceAll("[^\\w\\s]", "")
        .replaceAll("\\s+", "-")
        .toLowerCase();
  }
}
