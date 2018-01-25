package manager;



import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Employee;
import entity.EmployeeProject;
import entity.HourReport;
import entity.Project;

public class HourReportManager {

	private final EntityManager entityManager;

	public HourReportManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);	
	}

	public void update(HourReport hourreport) {
		entityManager.getTransaction().begin();
		entityManager.merge(hourreport);
		entityManager.getTransaction().commit();
	}

	public void create(HourReport hourreport) {
		entityManager.getTransaction().begin();
		entityManager.persist(hourreport);
		entityManager.getTransaction().commit();
	}

	public void delete(HourReport hourreport) {
		entityManager.getTransaction().begin();
		entityManager.remove(hourreport);
		entityManager.getTransaction().commit();
	}

	public HourReport get(Integer id) {
		return entityManager.find(HourReport.class, id);
	}
  
	public List<HourReport> getByDescription(String description) {
		String sql = "SELECT * From  hourreport where description like '";

		return (List<HourReport>) entityManager.createNativeQuery(sql + description + "'", HourReport.class)
				.getResultList();
	}
           // create hour report 
	public HourReport createHourReport(int employee, int project, String starthour, String endhour,
			String description) {
		Employee employee2 = ManagerHelper.getEmployee().get(employee);
		Project project2 = ManagerHelper.getProject().get(project);
System.out.println(starthour);
System.out.println(endhour);
		try {

			HourReport hourReport = new HourReport(employee2,project2,starthour,endhour,description);
			entityManager.getTransaction().begin();
			entityManager.persist(hourReport);
			entityManager.getTransaction().commit();
			return hourReport;

		} catch (Exception e) {
            e.printStackTrace();
			return null;

		}

	}
         // update hour report 
	public Reply update(int id, int employee, int project, String startdate, String enddate,
			String description) {
		
		Employee ep = ManagerHelper.getEmployee().get(employee);
		Project p = ManagerHelper.getProject().get(project);

		try {

			HourReport hourreport = new HourReport(id, ep, p, startdate, enddate, description);
			update(hourreport);
			return new Reply();

		} catch (Exception e) {
			e.printStackTrace();
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;

		}

	}

	public Reply deleteHourReport(int id) {
		try {
			HourReport hourreport = get(id);
			delete(hourreport);
			return new Reply();

		} catch (Exception e) {

			e.printStackTrace();
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}

	}

	public List<HourReport> getAllHourReports() {
		String sql ="select  h.id ,h.employee,h.project, DATE_FORMAT( h.startdate ,  '%Y-%m-%d' ) startdate, DATE_FORMAT( h.enddate ,  '%Y-%m-%d' ) enddate,h.description"
				+ " from projectmanager.hourreport h";
		return (List<HourReport>) entityManager.createNativeQuery(sql, HourReport.class).getResultList();
	}

	public List<HourReport> getHourReports(String yearAndMonth, int employee, int project, int customer) {
		String sql = "SELECT project.id,project.projectname,hourreport.description,hourreport.startdate," + " "
				+ "hourreport.enddate,timediff(time(hourreport.enddate)," + " "
				+ "time(hourreport.startdate)) from projectmanager.hourreport" + " "
				+ "inner join project on project.id = hourreport.id";

		return (List<HourReport>) entityManager.createNativeQuery(sql, HourReport.class).getResultList();

	}
        // gets all employess hour report list
	public List<HourReport> getEmployeeHr(Employee employee) {
		String sql = "select id,employee,project,startdate,enddate,description from hourreport where employee like '"
				+ employee + "'";
		return (List<HourReport>) entityManager.createNativeQuery(sql, HourReport.class).getResultList();

	}
  // gets the employess 7 last days of work
	public List<HourReport> getSevenDaysHr(int id) {

         String sql="select h.id,h.employee,h.project,h.startdate,h.enddate from hourreport h  inner join"+" "
        		    +"employee e on e.id = h.employee  inner join users u on u.id = e.user"+" "
        		    +"WHERE enddate BETWEEN DATE_SUB(CURDATE(), INTERVAL 7 DAY) AND CURDATE()"+" "
        		    +"and e.user ="+id;
		return (List<HourReport>) entityManager.createNativeQuery(sql, HourReport.class).getResultList();

	}
	        //gets the employee's hour report by their id
	public List<HourReport> getEmployeeHr(int id){
		
		String sql = "select * from hourreport"+" " 
				     +"inner join employee on employee.id = hourreport.employee"+" "
				     +"inner join users on users.id = employee.user and users.id = "+id;
		return (List<HourReport>) entityManager.createNativeQuery(sql,HourReport.class).getResultList();

	}//gets the customers hour report by their id
	public List<HourReport> getCustomerHr(int id){
		String sql = "sELECT  h.id , project.projectname , customer.companyname ,  DATE_FORMAT(h.startdate ,  '%Y-%m-%d' )startdate , DATE_FORMAT(h.enddate ,  '%Y-%m-%d' )enddate , h.description "
				+ "FROM projectmanager.hourreport h inner join project on project.id = h.project inner join customer on customer.id = project.customer inner join users on users.id = customer.user and users.id = "+id;
		return (List<HourReport>) entityManager.createNativeQuery(sql,HourReport.class).getResultList();
	
	
	}
	     // filters hour report by year and month only
	public List<HourReport> getByOnlyYearAndMonth(String yearandmonth,int employee,int customer, int project){
		
		
		String sql = "SELECT h.id , e.firstname , e.lastname , p.projectname , c.companyname , h.startdate , h.enddate , h.description From hourreport h inner join employee e on e.id = h.employee  inner join project p"
				     + " on p.id = h.project   inner join customer c on c.id = p.customer inner join users a on a.id =  c.user  where year "
				     + " (h.startdate) = year('"+yearandmonth+"-00') and month "
				     + "(h.startdate) = month('"+yearandmonth+"-00') ";
		  
		      if(employee!=0){
		    	  
		    	  sql +=" and h.employee ="+employee;
		      
		      }
               if(project!=0){
		    	  
		    	  sql +=" and h.project ="+project;
		      }
               if(customer!=0){
 		    	  
 		    	  sql +=" and c.user ="+customer;
 		      }
		
		return (List<HourReport>)entityManager.createNativeQuery(sql,HourReport.class).getResultList();
		
	}
	// create hourReport function for android 
	public HourReport createReport(int user, int project, String starthour, String endhour,
			String description) {
	String s=	"SELECT * FROM projectmanager.employee e inner join users u on e.user = u.id where u.id = "+user;
		 Employee employee2 = (Employee)entityManager.createNativeQuery(s,Employee.class).getSingleResult();
		 Project project2 = ManagerHelper.getProject().get(project);
         
		try {

			HourReport hourReport = new HourReport(employee2,project2,starthour,endhour,description);
			entityManager.getTransaction().begin();
			entityManager.persist(hourReport);
			entityManager.getTransaction().commit();
			return hourReport;

		} catch (Exception e) {
            e.printStackTrace();
			return null;

		}

	}
	
}
