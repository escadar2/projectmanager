package entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
public class HourReport {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="employee")
	private Employee employee;
	@ManyToOne
	@JoinColumn(name="project")
	private Project project;
	private String startdate;
	private String enddate;
	private String description;
	
	
	public HourReport(){
		
	}
	
	
	public HourReport(int id , Employee employee, Project project,String startdate , String enddate,String description){
		this.id = id;
		this.employee = employee;
		this.project = project;
		this.startdate = startdate;
		this.enddate = enddate;
		this.description = description;
	
		
	}
	
	public HourReport(Employee employee, Project project, String startdate, String enddate, String description) {
		this.employee = employee;
		this.project = project;
		this.startdate = startdate;
		this.enddate = enddate;
		this.description = description;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getStartdate() {
		return startdate; 
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	


}
