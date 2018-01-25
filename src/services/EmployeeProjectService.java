package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.EmployeeProject;
import entity.HourReport;
import manager.EmployeeProjectManager;
import manager.ManagerHelper;
import manager.Reply;

@Path("/employeeproject")
public class EmployeeProjectService {

	@GET
	@Path("getById")
	public EmployeeProject get(@QueryParam("id") int id) {
		return ManagerHelper.getEmployeeProject().get(id);

	}

	@GET
	@Path("create")
	public EmployeeProject CreateEmployeeProject(@QueryParam("employee") int employee,
		
			@QueryParam("project") int project) {
		System.out.println("as");
		return ManagerHelper.getEmployeeProject().createEmployeeProject(employee, project);

	}
	@GET
	@Path("getAllEp")
	public List<EmployeeProject> getAllEmployeeProject(){
		return ManagerHelper.getEmployeeProject().getAllEmployeeProject();
		
	}
	@GET
	@Path("delete")
	public Reply deleteEmployeeProject(@QueryParam("id")int id){
		System.out.println("in service");
		return ManagerHelper.getEmployeeProject().delete(id);
		
	}
	 @GET
		@Path("getProjectName")
	    public List<EmployeeProject> getProjectNameById(@QueryParam("id") int id){
			return ManagerHelper.getEmployeeProject().getProjectNameById(id);
			
		}

	

}
