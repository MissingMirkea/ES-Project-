package ro.foodwise.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.foodwise.model.*;
import java.util.List;
public interface FoodRescueOfferRepository extends JpaRepository<FoodRescueOffer,Long> { List<FoodRescueOffer> findByStatus(RescueStatus status); }
