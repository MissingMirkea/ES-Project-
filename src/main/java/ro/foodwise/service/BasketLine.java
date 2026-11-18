package ro.foodwise.service;

import ro.foodwise.model.PriceOffer;
import ro.foodwise.model.Product;

public record BasketLine(Product product, PriceOffer offer, double grams, double subtotal) {
    public double kilograms() { return grams / 1000.0; }
}
