import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
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
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		
	}
}
