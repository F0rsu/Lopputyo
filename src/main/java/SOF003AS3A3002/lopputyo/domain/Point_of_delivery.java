package SOF003AS3A3002.lopputyo.domain;


import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Point_of_delivery {

	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private long id;
    private String commercial_premise;
    private String address;
	
   
    
    @OneToMany(mappedBy = "pointofdelivery")
    @JsonIgnoreProperties("pointofdelivery")
    private List<Product> products = new ArrayList<>();
    
    
    
   
    
    
    
    
    
    public Point_of_delivery() {
	
	}



	public Point_of_delivery(String commercial_premise, String address) {
		super();
		this.id = 0;
		this.commercial_premise = commercial_premise;
		this.address = address;
	}



	public long getId() {
		return id;
	}



	public String getCommercial_premise() {
		return commercial_premise;
	}



	public String getAddress() {
		return address;
	}



	public void setId(long id) {
		this.id = id;
	}



	public void setCommercial_premise(String commercial_premise) {
		this.commercial_premise = commercial_premise;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	
	@Override
	public String toString() {
	    if (commercial_premise.isEmpty() && address.isEmpty()) {
	        return "";
	    }
	    return "Point_of_delivery [id=" + id + ", commercial_premise=" + commercial_premise + ", address=" + address + "]";
	}


	public List<Product> getProducts() {
	    return products;
	}




}
