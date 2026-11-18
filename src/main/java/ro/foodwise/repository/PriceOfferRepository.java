package ro.foodwise.repository;
import ro.foodwise.model.PriceOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PriceOfferRepository extends JpaRepository<PriceOffer,Long> { List<PriceOffer> findByProductId(Long productId); }
