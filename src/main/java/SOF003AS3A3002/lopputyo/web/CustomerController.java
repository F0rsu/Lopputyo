package SOF003AS3A3002.lopputyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import SOF003AS3A3002.lopputyo.domain.Customer;
import SOF003AS3A3002.lopputyo.domain.CustomerRepository;
import SOF003AS3A3002.lopputyo.domain.Point_of_deliveryRepository;
import SOF003AS3A3002.lopputyo.domain.ProductRepository;

@CrossOrigin
@Controller
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;

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


    @RequestMapping(value = "/api/customers", method = RequestMethod.POST)
    @ResponseBody
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

//http://localhost:8080/api/customers (post)


}