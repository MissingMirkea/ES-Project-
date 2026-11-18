package ro.foodwise;

import org.junit.jupiter.api.Test;
import ro.foodwise.controller.*;
import ro.foodwise.model.*;
import ro.foodwise.service.*;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApplicationConstructorTest {
    @Test void serviceAndControllerConstructorsAreCovered() {
        Product product = new Product("Pui", "Carne", 31, 165, 300, 500);
        Store store = new Store("Lidl", "București");
        PriceOffer offer = new PriceOffer(product, store, 21, LocalDate.now());
        BasketLine line = new BasketLine(product, offer, 300, 6.3);
        BasketSummary summary = new BasketSummary(6.3, 49.5, 9.3, 1.1, 0, 2, 18, 1, 0, List.of(line));

        assertNotNull(new FileDataService());
        assertNotNull(new WeeklyBasketStrategy());
        assertNotNull(new RoleAccessService());
        assertNotNull(new OfferService(null, null, null));
        assertNotNull(new FoodRescueService(null, null));
        assertNotNull(new ProductController(null, null));
        assertNotNull(new OfferController(null, null));
        assertNotNull(new RescueController(null));
        assertNotNull(new DatabaseDemoService(null));
        assertNotNull(new DatabaseDemoController(null));
        assertNotNull(new RoleDemoController(null));
        assertNotNull(new ApiExceptionHandler());
        assertNotNull(summary.lines());
    }
}
