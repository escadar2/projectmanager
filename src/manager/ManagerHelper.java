package manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagerHelper {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectmanager");

	

	public static CustomerManager getCustomerManager() {
		return new CustomerManager( entityManagerFactory.createEntityManager());
	}

	public static EmployeeManager getEmployee() {
		return new EmployeeManager( entityManagerFactory.createEntityManager());
	}

	public static HourReportManager getHourReport() {
		return new HourReportManager( entityManagerFactory.createEntityManager());
	}

	public static ProjectManager getProject() {
		return new ProjectManager( entityManagerFactory.createEntityManager());

	}
     public static UsersManager getUsers(){
	    System.out.println("in managerhelper");
    	 return new UsersManager( entityManagerFactory.createEntityManager());
    	 
     }
     public static EmployeeProjectManager getEmployeeProject(){
 		return new EmployeeProjectManager( entityManagerFactory.createEntityManager());
     	 
      }
   
}