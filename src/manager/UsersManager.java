package manager;

import java.util.List;

import javax.mail.MessagingException;
import javax.management.Query;
import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.Users;

public class UsersManager {
	private final EntityManager entityManager;

	public UsersManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(Users users) {
		entityManager.getTransaction().begin();
		entityManager.merge(users);
		entityManager.getTransaction().commit();
	}

	public void create(Users users) {
		entityManager.getTransaction().begin();
		entityManager.persist(users);
		entityManager.getTransaction().commit();
	}

	public void delete(Users users) {
		entityManager.getTransaction().begin();
		entityManager.remove(users);
		entityManager.getTransaction().commit();
	}

	public Users get(Integer id) {
		return entityManager.find(Users.class, id);
	}

	 
	public List<Users> getByName(String username) {
		String sql = "select * from users where username like '";
		return (List<Users>) entityManager.createNativeQuery(sql + username + "'", Users.class).getResultList();
	}
         // the login function  
	public Users getLogin(String username, String password) {
		try {
			String sql = "select * from users where username like '" + username + "'&& password like '" + password
					+ "'";
			return (Users) entityManager.createNativeQuery(sql, Users.class).getSingleResult();

		} catch (Exception e) {
			return null;

		}

	}
         // לא רלוונטי
	/*public Reply UserUpdate(int id, String username, String password, String type) {
		try {
			Users users = new Users(id, username, password, type);
			entityManager.getTransaction().begin();
			entityManager.merge(users);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			e.printStackTrace();
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;

		}

	}*/
         // func that deletes a user 
	public Reply DeleteUser(int id) {
		try {
			Users users = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(users);
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
                //  a func for creating user for both employee and customer
	public Users CreateUser(String username, String password, String type) {

		Users user1 = new Users(username, password, type);
		try {

			user1.setUsername(username);
			user1.setPassword(password);
			user1.setType(type);
			create(user1);

			return user1;

		} catch (Exception e) {

			return null;

		}

	}
           
	// פונקציית שחכתי סיסמה
	 
}
 