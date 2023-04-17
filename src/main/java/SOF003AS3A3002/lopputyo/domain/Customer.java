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
public class Customer {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 	private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
	
    
    @OneToMany(mappedBy = "customer")
    private List<Product> products = new ArrayList<>();
    
    
    
    
    
    
    
    public Customer() {
    	
    }
    
    
    
    public Customer(String firstname, String lastname, String email, String phonenumber) {
		super();
		this.id =0;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phonenumber = phonenumber;
	}



	public long getId() {
		return id;
	}



	public String getFirstname() {
		return firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public String getEmail() {
		return email;
	}



	public String getPhonenumber() {
		return phonenumber;
	}



	public void setId(long id) {
		this.id = id;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}



	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", phonenumber=" + phonenumber + "]";
	}



	public List<Product> getProducts() {
	    return products;
	}

    
    
    
    

}
