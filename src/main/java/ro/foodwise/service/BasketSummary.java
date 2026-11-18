package ro.foodwise.service;

import java.util.List;

public record BasketSummary(double totalPrice,double calories,double protein,double fat,double carbs,
                            double dailyCaloriesPercent,double dailyProteinPercent,double dailyFatPercent,
                            double dailyCarbsPercent, List<BasketLine> lines) { }
