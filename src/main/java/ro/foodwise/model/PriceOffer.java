package ro.foodwise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
public class PriceOffer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private Product product;
    @ManyToOne(optional=false) private Store store;
    @Positive private double pricePerKg;
    private LocalDate validUntil;
    protected PriceOffer() {}
    public PriceOffer(Product p,Store s,double price,LocalDate until){product=p;store=s;pricePerKg=price;validUntil=until;}
    public Long getId(){return id;} public Product getProduct(){return product;} public Store getStore(){return store;} public double getPricePerKg(){return pricePerKg;} public LocalDate getValidUntil(){return validUntil;}
    public void setPricePerKg(double v){pricePerKg=v;} public void setValidUntil(LocalDate v){validUntil=v;}
}
