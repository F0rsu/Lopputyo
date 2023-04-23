package SOF003AS3A3002.lopputyo.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	// Lisää tuote
	
	@RequestMapping(value = "/addproduct")
	public String showAddProductForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("deliveryPoints", pointOfDeliveryRepository.findAll());
		model.addAttribute("customers", customerRepository.findAll());
		return "addproduct";
	}

	// Näytä tuotelistaus
	
	@RequestMapping("/Products")
	public String products(Model model) {
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("customer", customerRepository.findAll());
		model.addAttribute("deliveryPoints", pointOfDeliveryRepository.findAll());
		return "Products";
	}

	
	
	

	
	
	
	// tallenna tuote
	
	@RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product, BindingResult result,
	        @RequestParam("pointOfDelivery.id") Long pointOfDeliveryId, @RequestParam("customer.id") Long customerId) {

	    if (result.hasErrors()) {
	        return "addproduct";
	    }
	    PointOfDelivery pointOfDelivery = pointOfDeliveryRepository.findById(pointOfDeliveryId).orElse(null);
	    Customer customer = customerRepository.findById(customerId).orElse(null);
	    product.setCustomer(customer);
	    product.setPointofdelivery(pointOfDelivery);
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
	
	
	
	@GetMapping("/editproduct/{id}")
	public String showEditProductForm(@PathVariable("id") Long id, Model model) {
	    Product product = productRepository.findById(id).orElse(null);
	    model.addAttribute("product", product);
	    model.addAttribute("deliveryPoints", pointOfDeliveryRepository.findAll());
	    model.addAttribute("customers", customerRepository.findAll());
	    return "Editproduct";
	}
	
	
	
	
	
	@PostMapping("/updateproduct")
	public String updateProduct(@ModelAttribute("product") Product product, BindingResult result,
	        @RequestParam("pointOfDeliveryId") Long pointOfDeliveryId, @RequestParam("customerId") Long customerId) {

	    if (result.hasErrors()) {
	        return "Editproduct";
	    }
	    PointOfDelivery pointOfDelivery = pointOfDeliveryRepository.findById(pointOfDeliveryId).orElse(null);
	    Customer customer = customerRepository.findById(customerId).orElse(null);
	    product.setCustomer(customer);
	    product.setPointofdelivery(pointOfDelivery);
	    productRepository.save(product);
	    return "redirect:/Products";
	}
	
	
	
	
	
	
	@PostMapping("/api/products")
	    public ResponseEntity<Product> addProduct(@RequestBody Product product,
	            @RequestParam("pointOfDeliveryId") Long pointOfDeliveryId,
	            @RequestParam("customerId") Long customerId) {

	        PointOfDelivery pointOfDelivery = pointOfDeliveryRepository.findById(pointOfDeliveryId).orElse(null);
	        Customer customer = customerRepository.findById(customerId).orElse(null);
	        product.setCustomer(customer);
	        product.setPointofdelivery(pointOfDelivery);
	        Product savedProduct = productRepository.save(product);
	        return ResponseEntity.created(URI.create("/api/products/" + savedProduct.getId())).body(savedProduct);
	    }
	// http://localhost:8080/api/products?pointOfDeliveryId=1&customerId=1  (post)


	 @GetMapping("/api/products")
	 public List<Product> getAllProducts() {
	     return (List<Product>) productRepository.findAll();
	 }


}
	 






