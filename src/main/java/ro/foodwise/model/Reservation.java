package ro.foodwise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private FoodRescueOffer offer;
    @NotBlank private String customerName;
    @Email @NotBlank private String email;
    @Positive private int portions;
    private LocalDateTime createdAt=LocalDateTime.now();
    protected Reservation() {}
    public Reservation(FoodRescueOffer offer,String customerName,String email,int portions){this.offer=offer;this.customerName=customerName;this.email=email;this.portions=portions;}
    public Long getId(){return id;} public FoodRescueOffer getOffer(){return offer;} public String getCustomerName(){return customerName;} public String getEmail(){return email;} public int getPortions(){return portions;} public LocalDateTime getCreatedAt(){return createdAt;}
}
