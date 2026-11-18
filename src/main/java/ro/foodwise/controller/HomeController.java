package ro.foodwise.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.foodwise.model.Goal;
import ro.foodwise.repository.ProductRepository;
import ro.foodwise.service.*;
import java.io.IOException;
import java.util.Set;
import java.util.ArrayList;

@Controller
public class HomeController {
    private final ProductRepository products; private final OfferService offers; private final FileDataService files; private final BasketStrategy strategy; private final FoodRescueService rescue;
    public HomeController(ProductRepository p,OfferService o,FileDataService f,BasketStrategy s,FoodRescueService rescue){products=p;offers=o;files=f;strategy=s;this.rescue=rescue;}
    @GetMapping("/") public String home(@RequestParam(required=false) String query,@RequestParam(required=false) Set<String> store,Model model){
        try { model.addAttribute("savedPreferences", files.readPreferences()); }
        catch (java.io.FileNotFoundException e) { model.addAttribute("preferencesMessage", "Nu există încă preferințe salvate."); }
        catch (IOException e) { model.addAttribute("preferencesMessage", "Fișierul de preferințe nu a putut fi citit."); }
        catch (NumberFormatException e) { model.addAttribute("preferencesMessage", "Bugetul salvat nu are un format numeric valid."); }
        return fill(model,null,null,null,query,store);
    }
    @PostMapping("/basket") public String basket(@RequestParam Goal goal,@RequestParam double budget,Model model){
        if(budget<=0) throw new IllegalArgumentException("Bugetul trebuie să fie pozitiv.");
        try{files.writePreferences(goal.name(),budget);}catch(IOException e){model.addAttribute("fileError","Preferințele nu au putut fi salvate.");}
        BasketSummary summary=calculate(goal,budget); model.addAttribute("selectedGoal",goal);model.addAttribute("budget",budget);model.addAttribute("summary",summary);model.addAttribute("message","Coșul pentru "+goal.name().toLowerCase()+" a fost generat.");return fill(model,goal,budget,summary);
    }
    @PostMapping("/reserve") public String reserve(@RequestParam Long offerId,@RequestParam String customerName,@RequestParam String email,@RequestParam int portions,Model model){try{rescue.reserve(offerId,customerName,email,portions);model.addAttribute("rescueMessage","Rezervarea a fost făcută. Ridică pachetul în intervalul afișat.");}catch(RuntimeException e){model.addAttribute("rescueError",e.getMessage());}return fill(model,null,null,null,null,null);}
    private String fill(Model model,Goal goal,Double budget,BasketSummary summary){return fill(model,goal,budget,summary,null,null);}
    private String fill(Model model,Goal goal,Double budget,BasketSummary summary,String query,Set<String> store){model.addAttribute("products",products.findAll());model.addAttribute("offers",offers.hunt(query,store));model.addAttribute("rescueOffers",rescue.available());if(goal!=null)model.addAttribute("selectedGoal",goal);if(budget!=null)model.addAttribute("budget",budget);if(summary!=null)model.addAttribute("summary",summary);return "index";}
    private BasketSummary calculate(Goal goal,double budget){
        double price=0,cal=0,protein=0,fat=0,carbs=0; var lines=new ArrayList<BasketLine>();
        for(var p:products.findAll()){var o=offers.cheapestOrNull(p.getId());if(o==null)continue;double grams=strategy.gramsFor(p,goal);double subtotal=o.getPricePerKg()*grams/1000;price+=subtotal;cal+=p.getCaloriesPer100g()*grams/100;protein+=p.getProteinPer100g()*grams/100;fat+=p.getFatPer100g()*grams/100;carbs+=p.getCarbsPer100g()*grams/100;lines.add(new BasketLine(p,o,grams,subtotal));}
        double scale=price>budget?budget/price:1;
        var scaledLines=lines.stream().map(l->new BasketLine(l.product(),l.offer(),round(l.grams()*scale,0),round(l.subtotal()*scale,2))).toList();
        return new BasketSummary(round(price*scale,2),Math.round(cal*scale),Math.round(protein*scale),Math.round(fat*scale),Math.round(carbs*scale),Math.round(cal*scale/2000*100),Math.round(protein*scale/50*100),Math.round(fat*scale/70*100),Math.round(carbs*scale/260*100),scaledLines);
    }
    private double round(double value,int decimals){double factor=Math.pow(10,decimals);return Math.round(value*factor)/factor;}
}
