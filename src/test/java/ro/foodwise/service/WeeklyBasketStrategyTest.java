package ro.foodwise.service;
import org.junit.jupiter.api.Test;
import ro.foodwise.model.*;
import static org.junit.jupiter.api.Assertions.*;
class WeeklyBasketStrategyTest {
 @Test void cutUsesCutQuantity(){Product p=new Product("Pui","Carne",30,160,300,500);assertEquals(300,new WeeklyBasketStrategy().gramsFor(p,Goal.CUT));}
 @Test void bulkUsesBulkQuantity(){Product p=new Product("Pui","Carne",30,160,300,500);assertEquals(500,new WeeklyBasketStrategy().gramsFor(p,Goal.BULK));}
 @Test void productConstructorStoresValues(){Product p=new Product("Orez","Carbohidrați",8,350,200,400);assertEquals("Orez",p.getName());assertEquals(350,p.getCaloriesPer100g());}
}
