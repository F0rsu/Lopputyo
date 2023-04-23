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
import SOF003AS3A3002.lopputyo.domain.PointOfDelivery;
import SOF003AS3A3002.lopputyo.domain.PointOfDeliveryRepository;
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
	private PointOfDeliveryRepository pointOfDeliveryRepository;
	
	
	@Test
	public void createNewProduct() {
		PointOfDelivery pointOfDelivery1 = new PointOfDelivery("Point1", "Helsinki");
	    pointOfDeliveryRepository.save(pointOfDelivery1);
		
		Customer customer1 = new Customer("John", "Doe", "john.doe@example.com", "0123456789");
	    customerRepository.save(customer1);
		Product product = new Product("Product1", "Type1", 10.99f, 1.5f, "Available", pointOfDelivery1, customer1);
	    
	    productRepository.save(product);
	    assertThat(product.getId()).isNotNull();
	}
}