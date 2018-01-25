package services;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import manager.ManagerHelper;
import manager.Reply;
import entity.Customer;
import entity.HourReport;
import entity.Project;

@Path("/project")

public class ProjectServices {

	@GET
	@Path("/get")

	public Project getProject(@QueryParam("id") int id) {
		return ManagerHelper.getProject().get(id);

	}

	@GET
	@Path("/getByName")
	public List<Project> getByName(@QueryParam("projectname") String projectname) {
		return ManagerHelper.getProject().getByName(projectname);
	}

	@GET
	@Path("getAllProjects")
    public List<Project> getAllProjects(){
		return ManagerHelper.getProject().getAllProject();
	}

   @GET
   @Path("activeproject")
   
   public List<Project> projectActive(@QueryParam("isActive") boolean isActive,@QueryParam("id")int id){
	   
	   return ManagerHelper.getProject().getActiveProject(isActive,id);
   }

   @GET
   @Path("getProjectsAboutToFinish")  
   
    public List<Project> getFinnishProject(){
		return ManagerHelper.getProject().getProjectsAboutToFinish();
    	
    	
    }
   
  @GET
  @Path("CreateProject")
  
  public Project CreateProject(@QueryParam("projectname") String projectname,@QueryParam("customer") int customer,@QueryParam("customerprojectmanager") String customerprojectmanager,@QueryParam("projectmanageremail") String projectmanageremail,@QueryParam("projectmanagerphone") String projectmanagerphone,
		   @QueryParam("startdate") String startdate,@QueryParam("enddate") String enddate){
			return ManagerHelper.getProject().CreateProject(projectname, customer, customerprojectmanager, projectmanageremail, projectmanagerphone, startdate, enddate);
	  
  }
  
  
       @GET
       @Path("DeleteProject")
              public Reply deleteproject(@QueryParam("id")int id){
				return ManagerHelper.getProject().delete(id);
    	   
       }
       @GET
       @Path("UpdateProject")
       public Reply UpdateProject(@QueryParam("id")int id,@QueryParam("projectname") String projectname,@QueryParam("customer") int customer,@QueryParam("customerprojectmanager") String customerprojectmanager,@QueryParam("projectmanageremail") String projectmanageremail,@QueryParam("projectmanagerphone") String projectmanagerphone,
		   @QueryParam("stratdate") String stratdate,@QueryParam("enddate") String enddate){
			return ManagerHelper.getProject().updateProject(id, projectname, customer, customerprojectmanager, projectmanageremail, projectmanagerphone, stratdate, enddate);
    	   
       }
       
       @GET
       @Path("getProjectByActive")
        public List<Project> getProjectByActive(@QueryParam("isActive")boolean isActive){
			if(isActive==true){
				  return ManagerHelper.getProject().getActiveProjects(isActive);		
			}else{
				return null;	
			}
			
    	 
    	   
       }
    
    @GET
   	@Path("BigCustomer")
   	public List<Project> getBigCustomers(){
   		return ManagerHelper.getProject().getBigCustomers();
   		
   	}
    
    @GET
    @Path("projectNameById")
    
    public List<Project> getProjectNameById(@QueryParam("id") int id){
		return ManagerHelper.getProject().getProjectNameById(id);
    	
    }
    

}
