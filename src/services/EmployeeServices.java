package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Employee;
import manager.EmployeeManager;
import manager.ManagerHelper;
import manager.Reply;

@Path("/employee")
public class EmployeeServices {

	@GET
	@Path("/get")
	public Employee getEmployee(@QueryParam("id") int id) {
		return ManagerHelper.getEmployee().get(id);

	}

	@GET
	@Path("/getByName")
	public List<Employee> getByName(@QueryParam("firstname") String firstname) {
		return ManagerHelper.getEmployee().getByName(firstname);
	}

	@GET
	@Path("deleteemployee")
	public Reply delete(@QueryParam("id")int id){
		return ManagerHelper.getEmployee().delete(id);
		
		
  }
	
	@GET
	@Path("UpdateEmployee")
	public Reply Update(@QueryParam("id")int id,@QueryParam("firstname")String firstname,@QueryParam("lastname")String lastname,@QueryParam("email")String email,@QueryParam("phone")String phone,@QueryParam("user")int user){
		return ManagerHelper.getEmployee().update(id, firstname, lastname, email, phone, user);
		
	}

	@GET
	@Path("CreateEmployee")
	
	public Employee CreateEmployee(@QueryParam("firstname")String firstname,@QueryParam("lastname")String lastname,@QueryParam("email")String email
			,@QueryParam("phone")String phone,@QueryParam("username")String username,@QueryParam("password")String password){
		System.out.println("sdsd");
		return ManagerHelper.getEmployee().createEmployee(firstname, lastname, email, phone, username,password);
		
	}
	@GET
	@Path("EmployeeList")
	public List<Employee> getEmployeeList(){
		return ManagerHelper.getEmployee().getAllEmployee();
		
	}
	
	
}
