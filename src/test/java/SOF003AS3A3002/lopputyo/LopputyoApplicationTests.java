package SOF003AS3A3002.lopputyo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import SOF003AS3A3002.lopputyo.web.ProductController;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class LopputyoApplicationTests {
	@Autowired
	private ProductController productController;
	
	
	
	
	
	
	@Test
	void contextLoads() {
		assertThat(productController).isNotNull();
	
	}

}
