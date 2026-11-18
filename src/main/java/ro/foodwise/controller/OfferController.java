package ro.foodwise.controller;
import org.springframework.web.bind.annotation.*;
import ro.foodwise.model.PriceOffer;
import ro.foodwise.model.AppUser;
import ro.foodwise.service.OfferService;
import ro.foodwise.service.RoleAccessService;
import java.util.List;
import java.util.Set;
@RestController @RequestMapping("/api/offers")
public class OfferController {
    private final OfferService service; private final RoleAccessService access; public OfferController(OfferService s,RoleAccessService access){service=s;this.access=access;}
    @GetMapping public List<PriceOffer> all(){return service.all();}
    @GetMapping("/hunt") public List<PriceOffer> hunt(@RequestParam(required=false) String query,
                                                       @RequestParam(required=false) Set<String> store){
        return service.hunt(query, store);
    }
    @GetMapping("/cheapest/{productId}") public PriceOffer cheapest(@PathVariable Long productId){return service.cheapestFor(productId);}
    @PostMapping public PriceOffer create(@RequestParam Long productId,@RequestParam Long storeId,@RequestParam double price,@RequestParam(defaultValue="SHOP_MANAGER") AppUser.Role role){access.require(access.canManageOffers(role),"Doar ADMIN sau SHOP_MANAGER poate publica oferte.");return service.save(productId,storeId,price);}
}
