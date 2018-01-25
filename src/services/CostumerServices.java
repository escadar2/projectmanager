package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Customer;

import manager.CustomerManager;
import manager.ManagerHelper;
import manager.Reply;

@Path("/customer")
public class CostumerServices {

	@GET
	@Path("/get")
	public Customer getCustomer(@QueryParam("id") int id) {
		return ManagerHelper.getCustomerManager().get(id);

	}

	@GET
	@Path("/getByName")
	public List<Customer> getByName(@QueryParam("companyname") String companyname) {

		return (List<Customer>) ManagerHelper.getCustomerManager().getByName(companyname);
	}

	@GET
	@Path("getStatus")
	public List<Customer> getCustomerByStatus(@QueryParam("isActive") boolean isActive) {
		if (isActive) {
			return (List<Customer>) ManagerHelper.getCustomerManager().getCustomerByStatusActive();
		} else {
			return (List<Customer>) ManagerHelper.getCustomerManager().getCustomerByAllActive();
		}

	}

	@GET
	@Path("createCustomer")
	public Customer createCustomer(@QueryParam("companyname") String companyname,
			@QueryParam("companynumber") String companynumber, @QueryParam("contactname") String contactname,
			@QueryParam("email") String email, @QueryParam("phone") String phone, @QueryParam("username") String username, @QueryParam("password") String password) {
			
		System.out.println("Sss");
		return ManagerHelper.getCustomerManager().createCustomer(companyname, companynumber, contactname, email, phone, username, password);
		

	}

	@GET
	@Path("/DeleteCustomer")
	public Reply delete(@QueryParam("id") int id) {

		return ManagerHelper.getCustomerManager().delete(id);

	}

	@GET
	@Path("/updatecustomer")
	public Reply Update(@QueryParam("id") int id,@QueryParam("companyname") String companyname,@QueryParam("companynumber") String companynumber,@QueryParam("contactname") String contactname,@QueryParam("email") String email,
			@QueryParam("phone") String phone,@QueryParam("user") int user) {

		return ManagerHelper.getCustomerManager().update(id, companyname, companynumber, contactname, email, phone, user);
				
	}
	@GET
	@Path("GetActiveCustomers")
	  public List<Customer> GetActiveCustomer(){
		return ManagerHelper.getCustomerManager().getCustomerByStatusActive();
		
	}
	
	@GET
	@Path("CustomerList")
	public List<Customer> GetAllCustomer(){
		return ManagerHelper.getCustomerManager().getAllCustomer();
		
	}
	
	  
	
}
