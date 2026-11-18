package ro.foodwise.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.foodwise.model.Product;
import ro.foodwise.model.AppUser;
import ro.foodwise.repository.ProductRepository;
import ro.foodwise.service.RoleAccessService;
import java.util.List;

@RestController @RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repository; private final RoleAccessService access;
    public ProductController(ProductRepository r,RoleAccessService access){repository=r;this.access=access;}
    @GetMapping public List<Product> all(){return repository.findAll();}
    @GetMapping("/{id}") public Product one(@PathVariable Long id){return repository.findById(id).orElseThrow();}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Product create(@Valid @RequestBody Product product,@RequestParam(defaultValue="ADMIN") AppUser.Role role){access.require(access.canManageProducts(role),"Doar ADMIN poate crea produse.");return repository.save(product);}
    @PutMapping("/{id}") public Product update(@PathVariable Long id,@Valid @RequestBody Product input,@RequestParam(defaultValue="ADMIN") AppUser.Role role){access.require(access.canManageProducts(role),"Doar ADMIN poate modifica produse.");Product p=one(id);p.setName(input.getName());p.setCategory(input.getCategory());p.setProteinPer100g(input.getProteinPer100g());p.setCaloriesPer100g(input.getCaloriesPer100g());p.setRecommendedCutGrams(input.getRecommendedCutGrams());p.setRecommendedBulkGrams(input.getRecommendedBulkGrams());return repository.save(p);}
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id,@RequestParam(defaultValue="ADMIN") AppUser.Role role){access.require(access.canManageProducts(role),"Doar ADMIN poate șterge produse.");repository.deleteById(id);}
}
