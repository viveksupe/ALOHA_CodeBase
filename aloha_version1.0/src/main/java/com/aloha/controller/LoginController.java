package com.aloha.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.aloha.common.dao_manager.dal.ImageDal;
import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.dao_manager.dal.UserEducationDal;
import com.aloha.common.dao_manager.dal.UserPersonalDal;
import com.aloha.common.entities.OnlineUsers;
import com.aloha.common.entities.user.User;
import com.aloha.common.entities.user.UserEducation;
import com.aloha.common.entities.user.UserPersonal;
import com.aloha.common.model.UserUI;
import com.aloha.common.sal.LoginService;
import com.aloha.common.util.ProfileImage;
import com.aloha.common.util.Secure_Hash;

@Controller
@SessionAttributes("sessionUser")
public class LoginController extends Secure_Hash{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/*
	 * renders the login page
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String Login(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome login! The client locale is {}.", locale);
		UserUI u = new UserUI();
		if(null==session.getAttribute("sessionUser")){
			return "Login";
		}else{
			u = (UserUI)session.getAttribute("sessionUser");
		}
		model.addAttribute("user",u);
		return "user_profile";
	}
	
	/*
	 * calls the service layer methods and delegates the task of login to login service layer
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String perform_login(@RequestParam("email") String email, @RequestParam("pwd") String pwd, Model model, HttpSession session){
		logger.info("Welcome login! The client locale is {}.");
		UserUI ui = new UserUI();
		LoginService login_service = new LoginService();

		if(null==session.getAttribute("sessionUser")){
			int res = login_service.perform_login(email, pwd, ui);
			if(res==1)
			{
				model.addAttribute("sessionUser",ui);
			}
		}else{			
			ui = (UserUI)session.getAttribute("sessionUser");
		}
		model.addAttribute("user",ui);
		
		return "redirect:"+"user_profile";	
	}

	
/*
 * old login method	
 */
/*public String perform_login(@RequestParam("email") String email, @RequestParam("pwd") String pwd, Model model, HttpSession session) {
		logger.info("Welcome login! The client locale is {}.");
		User u = new User();
		UserUI ui = new UserUI();
		if(null==session.getAttribute("sessionUser")){
			UserDal ud = new UserDal();
			User res= null;
			String ret = "Login";

			try {
				res = ud.getPasswordByEmail(email,pwd);
			} catch (SQLException e) {
				model.addAttribute("headerMessage","Something went wrong please try again");
			}
			if(res!=null)	
			{
				ui.setUser(res);
				OnlineUsers olUsers = new OnlineUsers();
				olUsers.addUserAsOnline(res.getUserId());
				model.addAttribute("sessionUser",ui);
				return "redirect:"+"user_profile";				
			}
			else
				model.addAttribute("headerMessage","email or password does not match please try again");
			return ret;
			
		}else{
			ui = (UserUI)session.getAttribute("sessionUser");
			model.addAttribute("user",ui);
			return "redirect:"+"user_profile";
		}		
	}*/
	
	@RequestMapping(value = "user_profile", method = RequestMethod.GET)
	public String display_user_profile(Model model, HttpSession session){
		UserUI u = new UserUI();
		UserEducation u_ed = null;
		UserPersonal up = null;
		LoginService login_service = new LoginService();
		if(null==session.getAttribute("sessionUser")){
			return "redirect:"+"login";
		}else{
			u = (UserUI)session.getAttribute("sessionUser");
			u_ed = login_service.getEducation(u);
			up = login_service.get_personal(u);				
		}
		model.addAttribute("education",u_ed);
		model.addAttribute("personal",up);
		model.addAttribute("user",u);		
		return "user_profile";
	}
	
	@RequestMapping(value = "displayimage", method = RequestMethod.GET)
	void display_image(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        int user_id = Integer.parseInt(id);
        ProfileImage pi = null;
        LoginService login_service = new LoginService();
		try {
			pi = login_service.display_image(user_id);
			if(pi!=null)
			{	
				response.setContentType("image/jpeg");
	            response.getOutputStream().write( pi.img);
	            response.getOutputStream().close();
			}
			else
			{
				response.getWriter().println("No Image");
	            response.getWriter().close();
			}
       
		} catch (Exception e) {
	
			e.printStackTrace();
		    response.getWriter().println("Sample text");
            response.getWriter().close();
		}
             
    }
	
	@RequestMapping(value = "forgotpassword", method = RequestMethod.GET)
	public String forgot_password_display(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome login! The client locale is {}.", locale);
		UserUI u = new UserUI();
		if(null==session.getAttribute("sessionUser")){
			return "forgotpassword";
		}else{
			u = (UserUI)session.getAttribute("sessionUser");
		}
		model.addAttribute("user",u);
		return "user_profile";
	}
	
	//please revert back
	@Autowired
    private JavaMailSender mailSender;
	@RequestMapping(value = "forgotpassword", method = RequestMethod.POST)
	public String forgot_password(@RequestParam("email") String email , Model model, HttpSession session){
		logger.info("Welcome login! The client locale is {}.");
		UserUI u = new UserUI();
		String res = "";
		LoginService login_service = new LoginService();
		if(null==session.getAttribute("sessionUser")){
			int out = login_service.forgot_password(email);
			if(out==1)
			{
				SimpleMailMessage emailobj = new SimpleMailMessage();
		        emailobj.setTo(email);
		        emailobj.setSubject("Password Reset Link");
		        emailobj.setText("Hello");
		         
		        // sends the e-mail
		        mailSender.send(emailobj);
		        res = "Check your inbox, A password reset link has been sent to "+email;
				
			}
			else
			{
				res = email+"no such user exists";
			}
			model.addAttribute("headerMessage",res);
			return "forgotpassword";
		}
		else{
			u = (UserUI)session.getAttribute("sessionUser");
			model.addAttribute("headerMessage","you are already logged in");
		}
		model.addAttribute("user",u);
		return "redirect:"+"logout";
	}
}
