package SOF003AS3A3002.lopputyo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PointOfDeliveryRepository extends CrudRepository<PointOfDelivery, Long>{
	List<Product> findByAddress(PointOfDelivery address);
}



