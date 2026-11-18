package ro.foodwise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank private String name;
    @NotBlank private String city;
    protected Store() {}
    public Store(String name,String city){this.name=name;this.city=city;}
    public Long getId(){return id;} public String getName(){return name;} public String getCity(){return city;}
    public void setName(String name){this.name=name;} public void setCity(String city){this.city=city;}
}
