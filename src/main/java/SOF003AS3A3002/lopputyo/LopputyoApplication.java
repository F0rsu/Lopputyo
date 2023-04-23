package SOF003AS3A3002.lopputyo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import SOF003AS3A3002.lopputyo.domain.Customer;
import SOF003AS3A3002.lopputyo.domain.CustomerRepository;
import SOF003AS3A3002.lopputyo.domain.PointOfDelivery;
import SOF003AS3A3002.lopputyo.domain.PointOfDeliveryRepository;
import SOF003AS3A3002.lopputyo.domain.Product;
import SOF003AS3A3002.lopputyo.domain.ProductRepository;



@SpringBootApplication
public class LopputyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LopputyoApplication.class, args);
	}




	@Bean
	public CommandLineRunner demoData(ProductRepository productRepository, CustomerRepository customerRepository, PointOfDeliveryRepository pointOfDeliveryRepository) {
	    return (args) -> {
	        Product product1 = new Product("Gambina", "Wine", 11.43f, 0.70f, "delivered", null ,null);
	        productRepository.save(product1);
	        
	        Product product2 = new Product("Valdemar", "Wine", 10.42f, 0.70f, "delivered", null, null);
	        productRepository.save(product2);
	        
	        //Testing for the database using only Product Entity
	        // update, now have to insert null and null since Entities have been connected
	        
	        
	        Customer customer1= new Customer ("Matti", "Meikälainen" , "matti@outlook.com" , "044123634512");
	        Customer customer2= new Customer ("Jaska", "Jokunen" , "jaska@outlook.com" , "044123634412");
	        Customer customer3= new Customer ("Liisa", "Kukanen" , "liisa@outlook.com" , "044223634512");
	        customerRepository.save(customer1);
	        customerRepository.save(customer2);
	        customerRepository.save(customer3);
	        		
	        // Adding few example customers for the database

	        
		
	        
	        PointOfDelivery point_of_delivery1 = new PointOfDelivery("Bar Joku", "Jokukatu12");
	        PointOfDelivery point_of_delivery2 = new PointOfDelivery("Bar Ketä", "Kukakatu56");
	        PointOfDelivery point_of_delivery3 = new PointOfDelivery("Bar Jotain", "Aleksanterinkatu2");
	        pointOfDeliveryRepository.save(point_of_delivery1);
	        pointOfDeliveryRepository.save(point_of_delivery2);
	        pointOfDeliveryRepository.save(point_of_delivery3);
	       
	        //adding few delivery places for the database
	        
	        Product product3 = new Product("Jägermaister", "Spirit" , 34.23f, 0.5f, "on delivery", point_of_delivery1, customer1);
	        productRepository.save(product3);
	      
	        
	        List<Product> products = (List<Product>) productRepository.findAll();
	        for (Product product: products) {
	            System.out.println(product.toString());
	        }
	    };
	}
}