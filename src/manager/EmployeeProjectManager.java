package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Employee;
import entity.EmployeeProject;
import entity.HourReport;
import entity.Project;



public class EmployeeProjectManager {

	private final EntityManager entityManager;

	public EmployeeProjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);	

	}
   
	
	public void create(EmployeeProject employeerojectmanager) {
		entityManager.getTransaction().begin();
		entityManager.persist(employeerojectmanager);
		entityManager.getTransaction().commit();
	}

	public void delete(EmployeeProject employeerojectmanager) {
		entityManager.getTransaction().begin();
		entityManager.remove(employeerojectmanager);
		entityManager.getTransaction().commit();
	}

	public void update(EmployeeProject employeerojectmanager) {
		entityManager.getTransaction().begin();
		entityManager.merge(employeerojectmanager);
		entityManager.getTransaction().commit();
	}
	public EmployeeProject get(Integer id){
		return entityManager.find(EmployeeProject.class,id);
		
	}

	
	  // create linking between employee and project attachemenet
	public EmployeeProject createEmployeeProject(int employee,int project){
		try{
		 	Project p  = ManagerHelper.getProject().get(project);
			Employee e = ManagerHelper.getEmployee().get(employee);
		 	EmployeeProject ep = new EmployeeProject(e,p);
			create(ep);
			return ep;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	   
	// delete employee project link
	public Reply delete(int id) {
		System.out.println("in managere");
		try{
			EmployeeProject ep = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(ep);
			entityManager.getTransaction().commit();
			return new Reply();
		}catch(Exception e){
			e.printStackTrace();
		  Reply r = new Reply();
		  r.setId(Reply.FAIL_ID);
		  r.setMsg(e.getMessage());
		  return r;
				  
		}
		
		
		
	}
	        // see all employee project links
	    public List<EmployeeProject> getAllEmployeeProject(){
		String sql = "SELECT * FROM projectmanager.employeeproject;";
		return (List<EmployeeProject>)entityManager.createNativeQuery(sql,EmployeeProject.class).getResultList();
		
	}
             
	    // get project name by employee identity
	    public List<EmployeeProject> getProjectNameById(int id ){
		      String sql =	"SELECT * FROM projectmanager.employeeproject ep "
		      		        + "inner join employee e on e.id = ep.employee "
		      		        + "inner  join users u on u.id = e.user where e.user="+id;
		    		  				
			return (List<EmployeeProject>)entityManager.createNativeQuery(sql,EmployeeProject.class).getResultList();

		    	
		    }
		
	   

}
