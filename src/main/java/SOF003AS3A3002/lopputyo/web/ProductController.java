package SOF003AS3A3002.lopputyo.web;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import SOF003AS3A3002.lopputyo.domain.Customer;
import SOF003AS3A3002.lopputyo.domain.CustomerRepository;
import SOF003AS3A3002.lopputyo.domain.PointOfDelivery;
import SOF003AS3A3002.lopputyo.domain.PointOfDeliveryRepository;
import SOF003AS3A3002.lopputyo.domain.Product;
import SOF003AS3A3002.lopputyo.domain.ProductRepository;

@Controller
public class ProductController {

	private final ProductRepository productRepository;
	private final PointOfDeliveryRepository pointOfDeliveryRepository;
	private final CustomerRepository customerRepository;

	@Autowired
	public ProductController(ProductRepository productRepository, PointOfDeliveryRepository pointOfDeliveryRepository,
			CustomerRepository customerRepository) {
		this.productRepository = productRepository;
		this.pointOfDeliveryRepository = pointOfDeliveryRepository;
		this.customerRepository = customerRepository;
	}


	

	// Näytä tuotelistaus
	
	@RequestMapping("/Products")
	public String products(Model model) {
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("customer", customerRepository.findAll());
		model.addAttribute("deliveryPoints", pointOfDeliveryRepository.findAll());
		return "Products";
	}

	
	

	

		
	
	
	
		
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute Product product, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        // Handle errors
	    }
	    productRepository.save(product);
	    return "redirect:/Products";
	}

	@GetMapping("/addproduct")
	public String showAddProductForm(Model model) {
	    Iterable<Customer> customers = customerRepository.findAll();
	    Iterable<PointOfDelivery> deliveryPoints = pointOfDeliveryRepository.findAll();
	    model.addAttribute("product", new Product());
	    model.addAttribute("customers", customers);
	    model.addAttribute("deliveryPoints", deliveryPoints);
	    return "Addproduct";
	}

	
	@GetMapping("/editproduct/{id}")
	public String showEditProductForm(@PathVariable("id") Long id, Model model) {
	    Product product = productRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + id));
	    Iterable<Customer> customers = customerRepository.findAll();
	    Iterable<PointOfDelivery> deliveryPoints = pointOfDeliveryRepository.findAll();
	    model.addAttribute("product", product);
	    model.addAttribute("customers", customers);
	    model.addAttribute("deliveryPoints", deliveryPoints);
	    return "Editproduct";
	}

	@PostMapping("/editproduct/{id}")
	public String editProduct(@PathVariable("id") Long id, @ModelAttribute Product product, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        // Handle errors
	    }
	    product.setId(id);
	    productRepository.save(product);
	    return "redirect:/Products";
	}
	
	
	
	@RequestMapping(value = "/deleteproduct/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("id") Long id) {
	    try {
	        productRepository.deleteById(id);
	        return "redirect:/Products";
	    } catch (EmptyResultDataAccessException e) {
	        return "redirect:/Products";
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
	 






