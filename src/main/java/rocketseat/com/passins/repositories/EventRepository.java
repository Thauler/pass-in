package rocketseat.com.passins.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.com.passins.domain.event.Event;

public interface EventRepository extends JpaRepository<Event, String> {
}
