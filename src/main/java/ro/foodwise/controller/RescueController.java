package ro.foodwise.controller;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.foodwise.model.*;
import ro.foodwise.service.FoodRescueService;
import java.util.List;

@RestController @RequestMapping("/api/rescue")
public class RescueController {
    private final FoodRescueService service; public RescueController(FoodRescueService service){this.service=service;}
    @GetMapping("/offers") public List<FoodRescueOffer> offers(){return service.available();}
    @PostMapping("/offers/{offerId}/reserve") @ResponseStatus(HttpStatus.CREATED) public Reservation reserve(@PathVariable Long offerId,@RequestParam @NotBlank String name,@RequestParam @Email String email,@RequestParam @Positive int portions){return service.reserve(offerId,name,email,portions);}
}
