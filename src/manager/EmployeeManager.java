package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Employee;
import entity.EmployeeProject;
import entity.Users;

public class EmployeeManager {

	private final EntityManager entityManager;

	public EmployeeManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);	
	}

	public void update(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.merge(employee);
		entityManager.getTransaction().commit();
	}

	public void create(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
	}

	public void delete(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.remove(employee);
		entityManager.getTransaction().commit();
	}

	public Employee get(Integer id) {
		return entityManager.find(Employee.class, id);
	}
// func for deleteing an employee and its user
	public Reply delete(int id) {
		try {
			
			
			Employee employee = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(employee);
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
           //updating an employee
	public Reply update(int id, String firstname, String lastname, String email, String phone, int user) {
		try {
			Users users = ManagerHelper.getUsers().get(user);
			Employee employee = new Employee(id, firstname, lastname, email, phone, users);
			entityManager.getTransaction().begin();
			entityManager.merge(employee);
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

	public List<Employee> getByName(String firstname) {
		String sql = "select * from employee where firstname like '";
		return (List) entityManager.createNativeQuery(sql + firstname + "'", Employee.class).getResultList();
	}
         // func to create an employee with its user
	public Employee createEmployee(String firstname, String lastname, String email, String phone, String username,String password) {
		try {
			Users users2 = new Users();
			users2.setUsername(username);
			users2.setPassword(password);
			users2.setType("employee");
			 ManagerHelper.getUsers().create(users2); 
			
			Employee employee = new Employee(firstname, lastname, email, phone, users2);
			create(employee);
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			Employee r = new Employee();
			
			return r;
		}

	}
   // func to get all employee list 
	public List<Employee> getAllEmployee() {

		String sql = "SELECT * FROM projectmanager.employee;";
		return (List<Employee>) entityManager.createNativeQuery(sql, Employee.class).getResultList();

	}
	

}
