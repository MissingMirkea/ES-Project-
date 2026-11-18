package ro.foodwise.repository;
import ro.foodwise.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product,Long> { java.util.Optional<Product> findByName(String name); }
