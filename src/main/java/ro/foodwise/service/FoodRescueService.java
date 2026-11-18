package ro.foodwise.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.foodwise.exception.InvalidBasketException;
import ro.foodwise.model.*;
import ro.foodwise.repository.*;
import java.util.List;

@Service
public class FoodRescueService {
    private final FoodRescueOfferRepository offers; private final ReservationRepository reservations;
    public FoodRescueService(FoodRescueOfferRepository offers,ReservationRepository reservations){this.offers=offers;this.reservations=reservations;}
    public List<FoodRescueOffer> available(){return offers.findByStatus(RescueStatus.AVAILABLE);}
    @Transactional public Reservation reserve(Long offerId,String name,String email,int portions){
        if(name==null||name.isBlank()||email==null||!email.contains("@")||portions<1) throw new InvalidBasketException("Completează corect numele, emailul și numărul de porții.");
        FoodRescueOffer offer=offers.findById(offerId).orElseThrow(()->new InvalidBasketException("Pachetul nu mai există."));
        if(offer.getStatus()!=RescueStatus.AVAILABLE||portions>offer.getPortions()) throw new InvalidBasketException("Pachetul nu mai are suficiente porții disponibile.");
        offer.setPortions(offer.getPortions()-portions); if(offer.getPortions()==0)offer.setStatus(RescueStatus.RESERVED); offers.save(offer);
        return reservations.save(new Reservation(offer,name,email,portions));
    }
}
