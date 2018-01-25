package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Users;
import manager.ManagerHelper;
import manager.Reply;

@Path("/users")
public class UsersService {
	@GET
	@Path("get")

	public Users getUsers(@QueryParam("id") int id) {
		return ManagerHelper.getUsers().get(id);

	}

	@GET
	@Path("getByName")
	public List<Users> getByName(@QueryParam("username") String username) {

		return (List<Users>) ManagerHelper.getUsers().getByName(username);
	}

	@GET
	@Path("Login")
	public Users getLogin(@QueryParam("username") String username, @QueryParam("password") String password) {

		return ManagerHelper.getUsers().getLogin(username, password);

	}
	
	/*@GET
	@Path("UserUpdate")
	public Reply UserUpdate(@QueryParam("id") int id,@QueryParam("username") String username, @QueryParam("password") String password,@QueryParam("type") String type){
		return ManagerHelper.getUsers().UserUpdate(id, username, password, type);
		
	}*/
	@GET
	@Path("DeleteUser")
	public Reply DeleteUser(@QueryParam("id")int id){
		return ManagerHelper.getUsers().DeleteUser(id);
		
	}
	@GET
	@Path("CreateUser")
	public Users CreateUser(@QueryParam("username")String username,@QueryParam("password")String password,@QueryParam("type")String type){
		return ManagerHelper.getUsers().CreateUser(username, password, type);
		
	}
	 
	
	
}