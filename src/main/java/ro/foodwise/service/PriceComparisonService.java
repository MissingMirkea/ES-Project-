package ro.foodwise.service;
import ro.foodwise.model.PriceOffer;
import java.util.List;
public interface PriceComparisonService { PriceOffer cheapest(List<PriceOffer> offers); }
