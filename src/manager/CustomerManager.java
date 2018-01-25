package manager;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Users;

public class CustomerManager {

	private final EntityManager entityManager;

	public CustomerManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);		

	}

	public void update(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.merge(customer);
		entityManager.getTransaction().commit();
	}

	public void create(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
	}

	public void delete(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.remove(customer);
		entityManager.getTransaction().commit();

	}

	//מחיקת לקוח המחיקה נעשית בפועל על ידי פונקצית מחיקת משתמש
	public Reply delete(int id) {
	
		
		try {
			Customer customer = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(customer);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			e.printStackTrace();
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}

	}
         
	
	//function for updating customer
	public Reply update(int id, String companyname, String companynumber, String contactname, String email,
			String phone, int user) {
		
	
		
		try {
			Users users = ManagerHelper.getUsers().get(user);
			Customer customer = new Customer(id, companyname, companynumber, contactname, email, phone,  users);
			update(customer);
			return new Reply();
		} catch (Exception e) {
			e.printStackTrace();
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}

	}

	public Customer get(Integer id) {
		return entityManager.find(Customer.class, id);
	}

	public List<Customer> getByName(String companyname) {
		String sql = "select * from customer where companyname like '";
		return (List<Customer>) entityManager.createNativeQuery(sql + companyname + "'", Customer.class)
				.getResultList();
	}
  //function to get active customers 
	public List<Customer> getCustomerByStatusActive() {

		String sql = "SELECT  c.id , c.companyname, c.companynumber,c.contactname,c.phone,c.email, true as isActive FROM customer c where("+" "
				     +"select count(p.id) from project p where p.customer = c.id"+" "
				     +"and (current_date() between p.startdate and p.enddate) > 0 )"+" ";
		return (List<Customer>) entityManager.createNativeQuery(sql, Customer.class).getResultList();

	}
     //function to see if a customer is active or not
	public List<Customer> getCustomerByAllActive() {
		String sql = "SELECT c.id, c.companyname,(" + " " + "select count(p.id) from project p" + " "
				+ "where p.customer = c.id" + " " + "and (current_date() between p.startdate and p.enddate)" + " "
				+ ") > 0 as 'isActive' , (select count(p.id) from project p" + " "
				+ "where p.customer = c.id ) as 'projectcount' from customer c";

		return (List<Customer>) entityManager.createNativeQuery(sql, Customer.class).getResultList();

	}
 //func for creating a new customer and its user
	public Customer createCustomer(String companyname, String companynumber, String contactname, String email,
			String phone, String username,String password ) {
		System.out.println("i in manager");
		
		try {
			Users user1 = ManagerHelper.getUsers().CreateUser(username, password, "customer");
			Customer customer = new Customer();
			customer.setCompanyname(companyname);
			customer.setCompanynumber(companynumber);
			customer.setContactname(contactname);
			customer.setEmail(email);
			customer.setPhone(phone);
			customer.setUser(user1);
			entityManager.getTransaction().begin();
			entityManager.persist(customer);
			entityManager.getTransaction().commit();
			return customer;

		} catch (Exception e) {
			e.printStackTrace();
		      Customer c = new Customer();
		      
			return c;
		}

	}
    
	// פונקצית רשימת לקוחות 
	public List<Customer> getAllCustomer() {
		String sql = "SELECT id,companyname,companynumber,contactname,email,phone,user FROM projectmanager.customer;";
		return (List<Customer>) entityManager.createNativeQuery(sql, Customer.class).getResultList();

	}
	
	

}
