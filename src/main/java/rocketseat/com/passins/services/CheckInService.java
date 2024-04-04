package rocketseat.com.passins.services;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passins.domain.attendee.Attendee;
import rocketseat.com.passins.domain.checkin.CheckIn;
import rocketseat.com.passins.domain.checkin.exceptions.CheckInAlreadyExists;
import rocketseat.com.passins.repositories.CheckInRepository;

@Service
@RequiredArgsConstructor
public class CheckInService {
  private final CheckInRepository checkInRepository;

  public void registerCheckIn(Attendee attendee) {
    this.verifyCheckInExists(attendee.getId());

    CheckIn newCheckIn = new CheckIn();
    newCheckIn.setAttendee(attendee);
    newCheckIn.setCreatedAt(LocalDateTime.now());

    this.checkInRepository.save(newCheckIn);
  }

  public Optional<CheckIn> getCheckIn(String attendeeId){
    return this.checkInRepository.findByAttendeeId(attendeeId);
  }

  private void verifyCheckInExists(String attendId){
    Optional<CheckIn> isCheckedIn = this.getCheckIn(attendId);
    if(isCheckedIn.isPresent()) throw new CheckInAlreadyExists("Attendee already exists!");
  }
}
