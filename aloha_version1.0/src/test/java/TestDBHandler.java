import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.entities.User;


public class TestDBHandler {
	static final Logger logger = Logger.getLogger(TestDBHandler.class.toString());

	public static void main(String[] args) {
		Connection con = DatabaseHandlerSingleton.getDBConnection();
		Date dob = Date.valueOf("1987-10-02");
		UserDal ud = new UserDal();
		try {
			int res;
			res = ud.insertUser(1, "milind", "gokhale",  "8123697654" , "milindhg@gmail.com", "root", dob, 0, 0, dob);
			if(res==1)
				System.out.println("row inserted successfully");
			User u = ud.selectUserByPrimaryKey(1);
			System.out.println(u.toString());
			res = ud.updateUser(1, "milind", "Hgokhale",  "8123697654" , "mgokhale@indiana.com", "root", dob, 0, 0, dob);
			if(res==1)
				System.out.println("row updated successfully");
			res = ud.deleteUser(1);
			if(res==1)
				System.out.println("row deleted successfully");
			
			res = ud.insertUser(1, "milind", "gokhale",  "8123697654" , "milindhg@gmail.com", "root", dob, 0, 0, dob);
			if(res==1)
				System.out.println("row inserted successfully");
			res = ud.insertUser(2, "renuka", "deshmukh",  "8123697654" , "renudesh@gmail.com", "root", dob, 0, 0, dob);
			if(res==1)
				System.out.println("row inserted successfully");
			res = ud.insertUser(3, "vivek", "supe",  "8123697654" , "vsupe@gmail.com", "root", dob, 0, 0, dob);
			if(res==1)
				System.out.println("row inserted successfully");
			res = ud.insertUser(4, "mrunal", "pagnis",  "8123697654" , "mpagnis@gmail.com", "root", dob, 0, 0, dob);
			if(res==1)
				System.out.println("row inserted successfully");


			ArrayList<User> ulist = ud.selectUserAll();
			
			for (User user : ulist){
				System.out.println(user);
			}
			
			res = ud.deleteUser(1);
			res = ud.deleteUser(2);
			res = ud.deleteUser(3);
			res = ud.deleteUser(4);

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		
	}
}
