package ro.foodwise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class FoodRescueOffer {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank private String title;
    @ManyToOne(optional=false) private Store store;
    @ManyToOne(optional=false) private Product product;
    @Positive private int portions;
    @Positive private double discountedPrice;
    @Positive private double originalPrice;
    private LocalDateTime pickupStart;
    private LocalDateTime pickupEnd;
    @Enumerated(EnumType.STRING) private RescueStatus status=RescueStatus.AVAILABLE;
    protected FoodRescueOffer() {}
    public FoodRescueOffer(String title,Store store,Product product,int portions,double discountedPrice,double originalPrice,LocalDateTime start,LocalDateTime end){this.title=title;this.store=store;this.product=product;this.portions=portions;this.discountedPrice=discountedPrice;this.originalPrice=originalPrice;pickupStart=start;pickupEnd=end;}
    public Long getId(){return id;} public String getTitle(){return title;} public Store getStore(){return store;} public Product getProduct(){return product;} public int getPortions(){return portions;} public double getDiscountedPrice(){return discountedPrice;} public double getOriginalPrice(){return originalPrice;} public LocalDateTime getPickupStart(){return pickupStart;} public LocalDateTime getPickupEnd(){return pickupEnd;} public RescueStatus getStatus(){return status;}
    public void setPortions(int v){portions=v;} public void setStatus(RescueStatus v){status=v;}
}
