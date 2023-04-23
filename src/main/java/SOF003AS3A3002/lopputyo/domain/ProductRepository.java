package SOF003AS3A3002.lopputyo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

	
	 List<Product> findByPointofdelivery(PointOfDelivery pointOfDelivery);

	List<Product> findByCustomer(Customer customer);
}





