package ro.foodwise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank private String name;
    @NotBlank private String category;
    @Positive private double proteinPer100g;
    @PositiveOrZero @Column(nullable=true) private Double fatPer100g;
    @PositiveOrZero @Column(nullable=true) private Double carbsPer100g;
    @PositiveOrZero private int caloriesPer100g;
    @Positive private double recommendedCutGrams;
    @Positive private double recommendedBulkGrams;
    protected Product() {}
    public Product(String name,String category,double protein,int calories,double cut,double bulk){this.name=name;this.category=category;proteinPer100g=protein;caloriesPer100g=calories;recommendedCutGrams=cut;recommendedBulkGrams=bulk;}
    public Product(String name,String category,double protein,double fat,double carbs,int calories,double cut,double bulk){this(name,category,protein,calories,cut,bulk);fatPer100g=fat;carbsPer100g=carbs;}
    public Long getId(){return id;} public String getName(){return name;} public String getCategory(){return category;} public double getProteinPer100g(){return proteinPer100g;} public double getFatPer100g(){return fatPer100g==null?0:fatPer100g;} public double getCarbsPer100g(){return carbsPer100g==null?0:carbsPer100g;} public int getCaloriesPer100g(){return caloriesPer100g;} public double getRecommendedCutGrams(){return recommendedCutGrams;} public double getRecommendedBulkGrams(){return recommendedBulkGrams;}
    public void setName(String v){name=v;} public void setCategory(String v){category=v;} public void setProteinPer100g(double v){proteinPer100g=v;} public void setFatPer100g(double v){fatPer100g=v;} public void setCarbsPer100g(double v){carbsPer100g=v;} public void setCaloriesPer100g(int v){caloriesPer100g=v;} public void setRecommendedCutGrams(double v){recommendedCutGrams=v;} public void setRecommendedBulkGrams(double v){recommendedBulkGrams=v;}
}
