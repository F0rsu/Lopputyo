package SOF003AS3A3002.lopputyo.web;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import SOF003AS3A3002.lopputyo.domain.Point_of_delivery;
import SOF003AS3A3002.lopputyo.domain.Point_of_deliveryRepository;

@CrossOrigin
@Controller
public class Point_of_deliveryController {

    @Autowired
    private Point_of_deliveryRepository point_of_deliveryRepository;

    @RequestMapping("/Addpoint_of_delivery")
    public String addPointOfDelivery(Model model) {
        model.addAttribute("point_of_delivery", new Point_of_delivery());
        return "Addpoint_of_delivery";
    }

    @RequestMapping(value = "/Addpoint_of_delivery", method = RequestMethod.POST)
    public String savePointOfDelivery(Point_of_delivery point_of_delivery) {
        point_of_deliveryRepository.save(point_of_delivery);
        return "redirect:/Point_of_deliveries";
    }

    
    @RequestMapping("/Point_of_deliveries")
    public String pointOfDeliveries(Model model) {
        model.addAttribute("point_of_deliveries", point_of_deliveryRepository.findAll());
        return "Point_of_deliveries";
    }


    @PostMapping("/api/points_of_delivery")
    public ResponseEntity<Point_of_delivery> addPointOfDelivery(@RequestBody Point_of_delivery point_of_delivery) {
        Point_of_delivery savedPointOfDelivery = point_of_deliveryRepository.save(point_of_delivery);
        return ResponseEntity.created(URI.create("/api/points_of_delivery/" + savedPointOfDelivery.getId())).body(savedPointOfDelivery);
    }
//http://localhost:8080/api/points_of_delivery (post)


}