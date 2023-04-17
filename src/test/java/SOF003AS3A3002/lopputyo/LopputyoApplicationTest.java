package SOF003AS3A3002.lopputyo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import SOF003AS3A3002.lopputyo.domain.Customer;
import SOF003AS3A3002.lopputyo.domain.CustomerRepository;
import SOF003AS3A3002.lopputyo.domain.Point_of_delivery;
import SOF003AS3A3002.lopputyo.domain.Point_of_deliveryRepository;
import SOF003AS3A3002.lopputyo.domain.Product;
import SOF003AS3A3002.lopputyo.domain.ProductRepository;
import SOF003AS3A3002.lopputyo.web.ProductController;

@ExtendWith(SpringExtension.class)  
@DataJpaTest
class LopputyoApplicationTest {

	@Autowired
	private ProductRepository productRepository;
	
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private Point_of_deliveryRepository point_of_deliveryRepository;
	
	
	@Test
	public void createNewProduct() {
		Point_of_delivery pointOfDelivery1 = new Point_of_delivery("Point1", "Helsinki");
	    point_of_deliveryRepository.save(pointOfDelivery1);
		
		Customer customer1 = new Customer("John", "Doe", "john.doe@example.com", "0123456789");
	    customerRepository.save(customer1);
		Product product = new Product("Product1", "Type1", 10.99f, 1.5f, "Available", pointOfDelivery1, customer1);
	    
	    productRepository.save(product);
	    assertThat(product.getId()).isNotNull();
	}
}