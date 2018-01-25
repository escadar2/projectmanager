package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.openjpa.persistence.EntityManagerImpl;


@Entity
public class Customer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String companyname;
	private String companynumber;
    private String contactname;
    private String email;
    private String phone; 
	
    private boolean isActive;
	@ManyToOne
	@JoinColumn(name="user")
	private Users user;
	
	

	public Customer(){
		
	}
	
	public Customer(String companyname,String companynumber,String contactname,String email,String phone,Users user,boolean isActive){
   
		this.companyname=companyname;
		this.companynumber = companynumber;
		this.contactname = contactname;
		this.email = email;
		this.phone = phone;
		this.user = user;
		this.isActive=isActive;
		
	}
	public Customer(int id, String companyname,String companynumber,String contactname,String email,String phone,Users user,boolean isActive){
		this.id=id;   
		this.companyname=companyname;
		this.companynumber = companynumber;
		this.contactname = contactname;
		this.email = email;
		this.phone = phone;
		this.user = user;
		this.isActive=isActive;
		
		
	}
	public Customer(String companyname,String companynumber,String contactname,String email,String phone,Users user){
		   
		this.companyname=companyname;
		this.companynumber = companynumber;
		this.contactname = contactname;
		this.email = email;
		this.phone = phone;
		this.user = user;
	
		
	}
	

	public Customer(int id, String companyname, String companynumber, String contactname, String email,
			String phone, Users user) {
		
		this.id=id;
		this.companyname=companyname;
		this.companynumber=companynumber;
		this.contactname=contactname;
		this.email=email;
		this.phone=phone;
		this.user=user;
				
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanynumber() {
		return companynumber;
	}
	public void setCompanynumber(String companynumber) {
		this.companynumber = companynumber;
	}
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}



	
	
	
}
