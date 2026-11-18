package ro.foodwise.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.foodwise.model.Reservation;
public interface ReservationRepository extends JpaRepository<Reservation,Long> { }
