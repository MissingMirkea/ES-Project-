package ro.foodwise.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.foodwise.model.*;
import ro.foodwise.repository.*;
import java.time.*;

@Configuration
public class DataInitializer {
 @Bean CommandLineRunner seed(ProductRepository p,StoreRepository s,PriceOfferRepository o,FoodRescueOfferRepository rescue){return args->{
  Store auchan=s.findByName("Auchan").orElseGet(()->s.save(new Store("Auchan","București")));Store lidl=s.findByName("Lidl").orElseGet(()->s.save(new Store("Lidl","București")));Store penny=s.findByName("Penny").orElseGet(()->s.save(new Store("Penny","București")));Store mega=s.findByName("Mega Image").orElseGet(()->s.save(new Store("Mega Image","București")));Store profi=s.findByName("Profi").orElseGet(()->s.save(new Store("Profi","București")));Store kaufland=s.findByName("Kaufland").orElseGet(()->s.save(new Store("Kaufland","București")));Store luca=s.findByName("Luca").orElseGet(()->s.save(new Store("Luca","București")));
  Product pork=product(p,"Carne de porc","Carne",21,18,0,242,2500,3500);Product chicken=product(p,"Piept de pui","Carne",31,3.6,0,165,3000,4500);Product rice=product(p,"Orez basmati","Carbohidrați",8,.7,77,350,2000,3000);Product eggs=product(p,"Ouă","Proteine",13,11,1,155,1200,1800);Product chocolate=product(p,"Ciocolată neagră","Gustare",7,43,46,598,300,500);Product yogurt=product(p,"Iaurt grecesc","Lactate",10,5,4,120,1500,2200);Product vegetables=product(p,"Legume mixte","Legume",3,.4,8,45,3500,4000);
  if(o.count()==0){offer(o,pork,auchan,15);offer(o,pork,lidl,25);offer(o,pork,penny,17);offer(o,pork,profi,19);offer(o,pork,kaufland,18);offer(o,chicken,auchan,24);offer(o,chicken,lidl,21);offer(o,chicken,profi,22);offer(o,rice,auchan,12);offer(o,rice,kaufland,10);offer(o,rice,penny,9);offer(o,eggs,lidl,16);offer(o,eggs,mega,14);offer(o,chocolate,auchan,32);offer(o,chocolate,lidl,28);offer(o,yogurt,auchan,14);offer(o,yogurt,kaufland,12);offer(o,yogurt,profi,11);offer(o,vegetables,lidl,9);offer(o,vegetables,auchan,11);offer(o,vegetables,penny,8);}
  if(rescue.count()==0){LocalDate today=LocalDate.now();rescue.save(new FoodRescueOffer("Pachet Luca - produse de patiserie",luca,vegetables,8,9.90,22.00,LocalDateTime.of(today,LocalTime.of(19,0)),LocalDateTime.of(today,LocalTime.of(21,0))));rescue.save(new FoodRescueOffer("Pachet Auchan - carne și garnitură",auchan,pork,5,18.50,37.00,LocalDateTime.of(today,LocalTime.of(20,0)),LocalDateTime.of(today,LocalTime.of(22,0))));rescue.save(new FoodRescueOffer("Pachet Mega Image - cină gata",mega,yogurt,6,12.00,24.00,LocalDateTime.of(today,LocalTime.of(20,0)),LocalDateTime.of(today,LocalTime.of(21,30))));}
 };}
 private Product product(ProductRepository r,String n,String c,double protein,double fat,double carbs,int cal,double cut,double bulk){return r.findByName(n).orElseGet(()->r.save(new Product(n,c,protein,fat,carbs,cal,cut,bulk)));}
 private void offer(PriceOfferRepository r,Product p,Store s,double price){r.save(new PriceOffer(p,s,price,LocalDate.now().plusDays(7)));}
}
