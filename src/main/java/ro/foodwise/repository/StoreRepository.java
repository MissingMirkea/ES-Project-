package ro.foodwise.repository;
import ro.foodwise.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StoreRepository extends JpaRepository<Store,Long> { java.util.Optional<Store> findByName(String name); }
