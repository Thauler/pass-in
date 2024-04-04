package rocketseat.com.passins.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.com.passins.domain.checkin.CheckIn;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
  Optional<CheckIn> findByAttendeeId(String attendeeId);
}
