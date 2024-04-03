package rocketseat.com.passins.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.com.passins.domain.attendee.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
}
