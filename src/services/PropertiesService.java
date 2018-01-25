package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import manager.Reply;

@Path("/properties")
public class PropertiesService {


@GET
@Path("gethours")
public String getHours(){
	String hours = PropsHelper.get("hours");
	return hours;
	
}
  @GET
  @Path("sethours")
  
  public String setHours (@QueryParam("worktime")String worktime){
	 
    System.out.println("in function");
	  PropsHelper.set("hours", worktime);
	return Reply.OK_STR;
  }
  
  @GET
  @Path("setdays")
  public String setDays(@QueryParam("sunday")String sunday,@QueryParam("monday")String monday,
		  @QueryParam("thirdday")String thirdday,@QueryParam("wednesday")String wednesday
        , @QueryParam("Thursday")String Thursday ,@QueryParam("friday")String friday ,
		  @QueryParam("saturday")String saturday){
	    PropsHelper.set("days",""+sunday+","+monday+","+thirdday+","+wednesday+","+Thursday+","+friday+","+saturday+"" );
	    return Reply.OK_STR;
	    
	  
	  
  }
  @GET
  @Path("getDays")
  public String getDays(){
	String days = PropsHelper.get("days");
	return days; 
  }
  
 
 
}
