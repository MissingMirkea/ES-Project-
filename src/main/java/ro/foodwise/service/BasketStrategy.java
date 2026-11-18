package ro.foodwise.service;
import ro.foodwise.model.Goal;
import ro.foodwise.model.Product;
public interface BasketStrategy { double gramsFor(Product product, Goal goal); }
