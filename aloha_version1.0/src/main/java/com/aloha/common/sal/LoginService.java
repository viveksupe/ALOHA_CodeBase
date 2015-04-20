package com.aloha.common.sal;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.common.dao_manager.dal.ImageDal;
import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.dao_manager.dal.UserEducationDal;
import com.aloha.common.dao_manager.dal.UserPersonalDal;
import com.aloha.common.entities.OnlineUsers;
import com.aloha.common.entities.user.User;
import com.aloha.common.entities.user.UserEducation;
import com.aloha.common.entities.user.UserPersonal;
import com.aloha.common.model.UserUI;
import com.aloha.common.util.ProfileImage;

public class LoginService {
	
	public int perform_login(String email, String pwd, UserUI ui) {
		UserDal ud = new UserDal();
		User res= null;
		try {
			res = ud.getPasswordByEmail(email,pwd);
		} catch (SQLException e) {
			return 0;
		}
		if(res!=null)	
		{
			ui.setUser(res);
			OnlineUsers olUsers = new OnlineUsers();
			olUsers.addUserAsOnline(res.getUserId());
			return 1;				
		}
		return 0;
	}
	
	public UserPersonal get_personal(UserUI u){
			UserPersonalDal updal = new UserPersonalDal();						
			UserPersonal up = new UserPersonal();
			try{
				up = updal.selectUserPersonalById(u.getUserId());
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
		
		return up;
	}

	public UserEducation getEducation(UserUI u) {
		// TODO Auto-generated method stub
		UserEducationDal ed = new UserEducationDal();
		UserEducation u_ed = new UserEducation();
		try {
			u_ed = ed.selectUserEducationById(u.getUserId());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u_ed;
	}
	
	public ProfileImage display_image(int user_id) {

        ImageDal imd = new ImageDal();
        ProfileImage pi = null;
		try {
			pi = imd.getProfileImage(user_id);
			
			    
		} catch (SQLException e) {
	
			e.printStackTrace();
		    
		}
         return pi;    
    }
	
	@Autowired
    private JavaMailSender mailSender;
	public int forgot_password(String email){
		UserUI u = new UserUI();
		UserDal ud = new UserDal();
		int res = 0;
		try {
				if(ud.checkIfUniqueEmail(email))
				{
					
					res = 1;
				}
				else
				{
					
					res = 0;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = 0;
		}
		return res;
		
	}
	
}
