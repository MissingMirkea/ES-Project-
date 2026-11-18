package ro.foodwise.service;
import org.springframework.stereotype.Component;
import ro.foodwise.model.*;
@Component
public class WeeklyBasketStrategy implements BasketStrategy {
    public double gramsFor(Product product, Goal goal) { return goal == Goal.CUT ? product.getRecommendedCutGrams() : product.getRecommendedBulkGrams(); }
}
