package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import manager.HourReportManager;
import manager.ManagerHelper;
import manager.Reply;
import entity.Employee;
import entity.EmployeeProject;
import entity.HourReport;
import entity.Project;

@Path("/hourreport")
public class HourReportServices {

	@GET
	@Path("/get")
	public HourReport getHourReport(@QueryParam("id") int id) {
		return ManagerHelper.getHourReport().get(id);

	}

	@GET
	@Path("/getByDescription")
	public List<HourReport> getByDescription(@QueryParam("description") String description) {
		return ManagerHelper.getHourReport().getByDescription(description);
	}

	@GET
	@Path("/createhourreport")
	public HourReport createHourReport( @QueryParam("employee") int employee,
			@QueryParam("project") int project, @QueryParam("starthour") String starthour,
			@QueryParam("endhour") String endhour,@QueryParam("description") String description) {
		return ManagerHelper.getHourReport().createHourReport(employee, project, starthour, endhour, description);
				

	}

	@GET
	@Path("deleteHourReport")

	public Reply deleteHourReport(@QueryParam("id") int id){
		
		return ManagerHelper.getHourReport().deleteHourReport(id);

		
	}

	@GET
	@Path("/getallhourreports")
	public List<HourReport> getAllHourReports() {
		return ManagerHelper.getHourReport().getAllHourReports();

	}

	@GET
	@Path("/gethourreports")
	public List<HourReport> getHourReports(@QueryParam("yearAndMonth") String yearAndMonth,
			@QueryParam("employee") int employee, @QueryParam("project") int project,
			@QueryParam("customer") int customer) {
		return ManagerHelper.getHourReport().getHourReports(yearAndMonth, employee, project, customer);

	}

	 @GET
	 @Path("UpdateHourReport")
	  public Reply UpdateHourReport(@QueryParam("id") int id, @QueryParam("employee") int employee,
				@QueryParam("project") int project, @QueryParam("startdate") String startdate,
				@QueryParam("enddate") String enddate, @QueryParam("description") String description){
					return ManagerHelper.getHourReport().update(id, employee, project, startdate, enddate, description);
		 
		 
	 }
	 @GET
	 @Path("s")
	 public List<HourReport> getEmployeehr(Employee employee){
		return ManagerHelper.getHourReport().getEmployeeHr(employee);
		 
	 }
	 
	 @GET
	 @Path("getSevenDaysBack")
	 
	 public List<HourReport>getSevenDaysHr(@QueryParam("id")int id){
		return ManagerHelper.getHourReport().getSevenDaysHr(id);
		 
	 }
	 @GET
	 @Path("getEmployeeHr")
	 
	 public List<HourReport> getEmployeeHr(@QueryParam("id")int id){
		return ManagerHelper.getHourReport().getEmployeeHr(id);
		 
	 }
	 @GET
	 @Path("getByYearAndMonth")
	 public List<HourReport> getByYearAndMonth(@QueryParam("yearandmonth")String yearandmonth,@QueryParam("employee") int employee,@QueryParam("customer") int customer,@QueryParam("project") int project 
			 ) {
		return (List<HourReport>)ManagerHelper.getHourReport().getByOnlyYearAndMonth(yearandmonth, employee, customer, project);
				
		 
		 
	 }
	 @GET
	 @Path("getCustomerHr")
	 public List<HourReport>getCustomerHr(@QueryParam("id") int id){
		return (List<HourReport>)ManagerHelper.getHourReport().getCustomerHr(id);
		 
	 }
	 @GET
		@Path("/createreport")
		public HourReport createReport( @QueryParam("employee") int user,
				@QueryParam("project") int project, @QueryParam("starthour") String starthour,
				@QueryParam("endhour") String endhour,@QueryParam("description") String description) {
			return ManagerHelper.getHourReport().createReport(user, project, starthour, endhour, description);
					

		}
	
	
	 
	
}
