package SOF003AS3A3002.lopputyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import SOF003AS3A3002.lopputyo.domain.Customer;
import SOF003AS3A3002.lopputyo.domain.CustomerRepository;
import SOF003AS3A3002.lopputyo.domain.PointOfDeliveryRepository;
import SOF003AS3A3002.lopputyo.domain.Product;
import SOF003AS3A3002.lopputyo.domain.ProductRepository;

@CrossOrigin
@Controller
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;
    
    
    
    @RequestMapping("/Customers")
    public String customers(Model model) {
       model.addAttribute("customers", customerRepository.findAll());
       return "Customers";  
    }
   
    @RequestMapping(value="/addcustomer")
    public String addCustomer(Model model) {
    	model.addAttribute("customer", new Customer());
    	return "Addcustomer";
    }

    
   
    @RequestMapping(value = "/save-customer", method = RequestMethod.POST)
    public String saveCustomer(Customer customer) {
    	customerRepository.save(customer);
    	return "redirect:/Customers";
    }   

    
    @PostMapping("/delete-customer")
    public String deleteCustomer(@RequestParam("id") Long id, Model model) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            
            // Check if the customer is associated with any Product entity
            List<Product> products = productRepository.findByCustomer(customer);
            if (!products.isEmpty()) {
                // If the customer is associated with any Product entity, set an error message and redirect back to the Customer list page
                model.addAttribute("errorMessage", "The Customer is associated with " + products.size() + " Product(s) and cannot be deleted.");
                model.addAttribute("customers", customerRepository.findAll());
                return "Customers";
            }
            
            // If the customer is not associated with any Product entity, delete it and redirect back to the Customer list page
            customerRepository.delete(customer);
        }
        
        // Redirect back to the Customer list page
        return "redirect:/Customers";
    }
    
    
    
    
    
    



}