package SOF003AS3A3002.lopputyo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String type;
	private float price;
	private float litres;
	private String status;

	@ManyToOne
	@JsonIgnoreProperties ("products") 
	@JoinColumn(name = "customerid", referencedColumnName = "id")
    private Customer customer;
	
    @ManyToOne
    @JoinColumn(name = "pointofdeliveryid", referencedColumnName = "id")
    private PointOfDelivery pointofdelivery;

	public Product() {
		this.name = "";
		this.type = "";
		this.price = 0.0f;
		this.litres = 0.0f;
		this.status = "";
	}

	public Product(String name, String type, float price, float litres, String status, PointOfDelivery pointofdelivery, Customer customer) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.litres = litres;
		this.status = status;
	    this.pointofdelivery=pointofdelivery;
	    this.customer=customer;
	}


	

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public float getPrice() {
		return price;
	}

	public float getLitres() {
		return litres;
	}

	public String getStatus() {
		return status;
	}

	
	
	
	public Customer getCustomer () {
		return customer;
	}
	
	
	
	
	
	
	public void setCustomer (Customer customer) {
		this.customer=customer;
	}
	
	
	
	
	public PointOfDelivery getPointofdelivery() {
		return pointofdelivery;
	}
	
	
	public void setPointofdelivery (PointOfDelivery pointofdelivery) {
		this.pointofdelivery=pointofdelivery;
	}
	
	
	
	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setLitres(float litres) {
		this.litres = litres;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		if(this.pointofdelivery != null && this.customer != null)
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + ", litres=" + litres
				+ ", status=" + status + ",  pointofdelivery=" + pointofdelivery + ", customer=" + customer +"]";
	
	   if(this.pointofdelivery != null)
		   return "Product [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + ", litres=" + litres
					+ ", status=" + status;
	
	   if(this.customer != null)
		   return "Product [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + ", litres=" + litres
					+ ", status=" + status + ", customer=" + customer +"]";
	
	   else
		   return "Product [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + ", litres=" + litres
					+ ", status=" + status;
	}
              
	
	
	
	
	
	
	
}
