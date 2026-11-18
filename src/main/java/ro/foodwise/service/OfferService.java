package ro.foodwise.service;

import org.springframework.stereotype.Service;
import ro.foodwise.exception.OfferNotFoundException;
import ro.foodwise.model.*;
import ro.foodwise.repository.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OfferService implements PriceComparisonService {
    private final PriceOfferRepository offers; private final ProductRepository products; private final StoreRepository stores;
    public OfferService(PriceOfferRepository o,ProductRepository p,StoreRepository s){offers=o;products=p;stores=s;}
    public List<PriceOffer> all(){return offers.findAll();}
    public List<PriceOffer> hunt(String query, Set<String> storeNames){
        String normalized = query == null ? "" : query.trim().toLowerCase(Locale.ROOT);
        return all().stream()
                .filter(o -> normalized.isBlank() || o.getProduct().getName().toLowerCase(Locale.ROOT).contains(normalized)
                        || o.getProduct().getCategory().toLowerCase(Locale.ROOT).contains(normalized))
                .filter(o -> storeNames == null || storeNames.isEmpty() || storeNames.stream().anyMatch(s -> o.getStore().getName().equalsIgnoreCase(s)))
                .sorted(Comparator.comparingDouble(PriceOffer::getPricePerKg))
                .collect(Collectors.toList());
    }
    public PriceOffer cheapest(List<PriceOffer> list){return list.stream().min(Comparator.comparingDouble(PriceOffer::getPricePerKg)).orElseThrow(()->new OfferNotFoundException("Nu există ofertă pentru produs."));}
    public PriceOffer cheapestFor(Long productId){return cheapest(offers.findByProductId(productId));}
    public PriceOffer save(Long productId,Long storeId,double price){
        Product p=products.findById(productId).orElseThrow(()->new OfferNotFoundException("Produs inexistent."));
        Store s=stores.findById(storeId).orElseThrow(()->new OfferNotFoundException("Magazin inexistent."));
        if(price<=0) throw new IllegalArgumentException("Prețul trebuie să fie pozitiv.");
        return offers.save(new PriceOffer(p,s,price,java.time.LocalDate.now().plusDays(7)));
    }
    public PriceOffer cheapestOrNull(Long productId){List<PriceOffer> list=offers.findByProductId(productId);return list.isEmpty()?null:cheapest(list);}
}
