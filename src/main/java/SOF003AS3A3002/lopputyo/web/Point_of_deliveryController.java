package SOF003AS3A3002.lopputyo.web;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import SOF003AS3A3002.lopputyo.domain.PointOfDelivery;
import SOF003AS3A3002.lopputyo.domain.PointOfDeliveryRepository;
import SOF003AS3A3002.lopputyo.domain.Product;
import SOF003AS3A3002.lopputyo.domain.ProductRepository;

@CrossOrigin
@Controller
public class Point_of_deliveryController {

    @Autowired
    private PointOfDeliveryRepository pointOfDeliveryRepository;

    
    @Autowired
    private ProductRepository productRepository;

    
    
    
    
    
    @RequestMapping("/addpoint_of_delivery")
    public String addPointOfDelivery(Model model) {
        model.addAttribute("point_of_delivery", new PointOfDelivery());
        return "Addpoint_of_delivery";
    }

    @RequestMapping(value = "/Addpoint_of_delivery", method = RequestMethod.POST)
    public String savePointOfDelivery(PointOfDelivery pointOfDelivery) {
        pointOfDeliveryRepository.save(pointOfDelivery);
        return "redirect:/Point_of_deliveries";
    }

    
   
  
    
    
    
    
    
    
    
    @RequestMapping("/Point_of_deliveries")
    public String pointOfDeliveries(Model model) {
        model.addAttribute("point_of_deliveries", pointOfDeliveryRepository.findAll());
        return "Point_of_deliveries";
    }


   
    
   
    
    
    
    
    
    
    
    
    
    @PostMapping("/point_of_delivery/delete")
    public String deletePointOfDelivery(@RequestParam("id") Long id, Model model) {
        Optional<PointOfDelivery> optionalPointOfDelivery = pointOfDeliveryRepository.findById(id);
        if (optionalPointOfDelivery.isPresent()) {
            PointOfDelivery pointOfDelivery = optionalPointOfDelivery.get();
            
            // Check if the Point_of_delivery is associated with any Product entity
            List<Product> products = productRepository.findByPointofdelivery(pointOfDelivery);
            if (!products.isEmpty()) {
                // If the Point_of_delivery is associated with any Product entity, set an error message and redirect back to the Point_of_delivery list page
                String errorMessage = "The Point_of_delivery is associated with " + products.size() + " Product(s) and cannot be deleted.";
                model.addAttribute("errorMessage", errorMessage);
                model.addAttribute("point_of_deliveries", pointOfDeliveryRepository.findAll());
                return "Point_of_deliveries";
            }
            
            try {
                // If the Point_of_delivery is not associated with any Product entity, delete it and redirect back to the Point_of_delivery list page
                pointOfDeliveryRepository.delete(pointOfDelivery);
            } catch (Exception e) {
                // If an exception occurs while deleting the Point_of_delivery, set an error message and redirect back to the Point_of_delivery list page
                String errorMessage = "An error occurred while deleting the Point_of_delivery: " + e.getMessage();
                model.addAttribute("errorMessage", errorMessage);
                model.addAttribute("point_of_deliveries", pointOfDeliveryRepository.findAll());
                return "Point_of_deliveries";
            }
        }
        
        // Redirect back to the Point_of_delivery list page
        return "redirect:/Point_of_deliveries";
    }
    
    
    
    
    
    
    
  


}