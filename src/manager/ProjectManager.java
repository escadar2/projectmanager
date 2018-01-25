

package manager;

import javax.persistence.EntityManager;



import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;

import entity.Project;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProjectManager {

	private final EntityManager entityManager;

	public ProjectManager(EntityManager entitymanager) {
		this.entityManager = entitymanager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);	
	}

	public void update(Project project) {
		entityManager.getTransaction().begin();
		entityManager.merge(project);
		entityManager.getTransaction().commit();

	}

	public void create(Project project) {
		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
	}

	public void delete(Project project) {
		entityManager.getTransaction().begin();
		entityManager.remove(project);
		entityManager.getTransaction().commit();

	}

	public Project get(Integer id) {
		return entityManager.find(Project.class, id);

	}

	public List<Project> getByName(String projectname) {
		String sql = "select * from project where projectname like '";
		return (List<Project>) entityManager.createNativeQuery(sql + projectname + "'", Project.class).getResultList();
	}
	 
     // func to get all projects 
	public List<Project> getAllProject() {
		String sql = "select  p.id,p.projectname,p.customer,p.customerprojectmanager,p.projectmanageremail,p.projectmanagerphone "
				    + ",DATE_FORMAT( p.startdate ,  '%Y-%m-%d' )startdate ,DATE_FORMAT( p.enddate ,  '%Y-%m-%d' )enddate "
				     + "from project p ";

		return (List<Project>) entityManager.createNativeQuery(sql, Project.class).getResultList();

	}
           //get active projects by customer
	public List<Project> getActiveProject(boolean isActive, int id) {
		String sql = "select p.id ,p.projectname ,DATE_FORMAT( p.startdate ,  '%Y-%m-%d' )startdate ,DATE_FORMAT( p.enddate ,  '%Y-%m-%d' )enddate, true as 'isActive' "
				+ "from project p inner join customer on customer.id = p.customer inner join users on users.id = customer.user where((current_date() between p.startdate and p.enddate)>0) and users.id ="+id;


		if (isActive == true) {
			return (List<Project>) entityManager.createNativeQuery(sql, Project.class).getResultList();
		}
		return null;
	}
               // create project 
	public Project CreateProject(String projectname, int customer, String customerprojectmanager,
			String projectmanageremail, String projectmanagerphone, String startdate, String enddate) {
		
		
		
		
		Customer c = ManagerHelper.getCustomerManager().get(customer);

		try {

			Project project = new Project(projectname, c, customerprojectmanager, projectmanageremail,
					projectmanagerphone, startdate, enddate);
			create(project);

			return project;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}
                   // func to delete project
	public Reply delete(int id) {
		try {
			Project project = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(project);
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
             // func to update a project 
	public Reply updateProject(int id, String projectname, int customer, String customerprojectmanager,
			String projectmanageremail, String projectmanagerphone, String stratdate, String enddate) {

		Customer c = ManagerHelper.getCustomerManager().get(customer);
		try {
			Project project = new Project(id, projectname, c, customerprojectmanager, projectmanageremail,
					projectmanagerphone, stratdate, enddate);
			update(project);
			System.out.println("dfdf");
			return new Reply();

		} catch (Exception e) {
			e.printStackTrace();
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;

		}

	}
                   // gets list of projects abut to finish
	public List<Project> getProjectsAboutToFinish() {
		String sql = "select id ,projectname, customer ,customerprojectmanager,projectmanageremail,projectmanagerphone,DATE_FORMAT(startdate ,  '%Y-%m-%d' )startdate , DATE_FORMAT(enddate ,  '%Y-%m-%d' )enddate from project WHERE enddate BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 90 DAY)";

		return (List<Project>) entityManager.createNativeQuery(sql, Project.class).getResultList();

	}

              // gets active projects only
	public List<Project> getActiveProjects(boolean isActive) {
		String sql = "select p.id ,p.projectname,   DATE_FORMAT(startdate ,  '%Y-%m-%d' )startdate ,  DATE_FORMAT(enddate ,  '%Y-%m-%d' )enddate, true as 'isActive' "
				+ "from project p inner join customer on customer.id = p.customer where((current_date() between p.startdate and  p.enddate)>0)";
		return (List<Project>) entityManager.createNativeQuery(sql, Project.class).getResultList();
	}
	
	 // gets  customers who have more  projects  than  the number spicified in the sql
	public List<Project> getBigCustomers(){
		String sql = "SELECT c.id, c.companyname,c.companynumber,c.contactname,c.email, c.phone ,"+" "
       +" COUNT(c.id)  as count  from project p"+" "
       +" inner join customer c on c.id=p.customer   group by c.companyname"+" "
       +"having count(c.id) > 1";

		return (List<Project>) entityManager.createNativeQuery(sql,Project.class).getResultList();
	}
	    // ff
	public List<Project> getProjectNameById(int id){
		
		String sql = "SELECT * FROM projectmanager.project p "
				+ "inner join customer c on c.id = p.customer inner  "
				+ "join users u on u.id = c.user where c.user = "+id;
		return (List<Project>) entityManager.createNativeQuery(sql,Project.class).getResultList();
		
	}
        
	
}
