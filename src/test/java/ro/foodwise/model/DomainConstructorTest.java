package ro.foodwise.model;

import org.junit.jupiter.api.Test;
import java.time.*;
import static org.junit.jupiter.api.Assertions.*;

class DomainConstructorTest {
    @Test void productConstructorsStoreDomainValues() {
        Product basic = new Product("Orez", "Carbohidrați", 8, 350, 200, 400);
        Product detailed = new Product("Pui", "Carne", 31, 3.6, 0, 165, 300, 500);
        assertAll(() -> assertEquals("Orez", basic.getName()), () -> assertEquals(3.6, detailed.getFatPer100g()));
    }

    @Test void storeOfferAndUserConstructorsCreateObjects() {
        Store store = new Store("Lidl", "București");
        Product product = new Product("Pui", "Carne", 31, 165, 300, 500);
        PriceOffer offer = new PriceOffer(product, store, 21, LocalDate.now());
        AppUser user = new AppUser("Ana", "ana@example.com", AppUser.Role.CLIENT);
        assertAll(() -> assertEquals("Lidl", store.getName()), () -> assertEquals(21, offer.getPricePerKg()),
                () -> assertEquals(AppUser.Role.CLIENT, user.getRole()));
    }

    @Test void rescueAndReservationConstructorsCreateObjects() {
        Store store = new Store("Auchan", "București");
        Product product = new Product("Pui", "Carne", 31, 165, 300, 500);
        FoodRescueOffer rescue = new FoodRescueOffer("Pachet", store, product, 2, 10, 20,
                LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        Reservation reservation = new Reservation(rescue, "Ana", "ana@example.com", 1);
        assertAll(() -> assertEquals(2, rescue.getPortions()), () -> assertEquals("Ana", reservation.getCustomerName()));
    }
}
