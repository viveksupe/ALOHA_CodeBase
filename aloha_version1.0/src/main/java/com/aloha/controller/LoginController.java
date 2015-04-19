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
import com.aloha.common.util.ProfileImage;
import com.aloha.common.util.Secure_Hash;

@Controller
@SessionAttributes("sessionUser")
public class LoginController extends Secure_Hash{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
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
	@RequestMapping(value = "editprofile", method = RequestMethod.GET)
	public String edit_profile(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome login! The client locale is {}.", locale);
		UserUI u = new UserUI();
		if(null==session.getAttribute("sessionUser")){
			return "Login";
		}else{
			u = (UserUI)session.getAttribute("sessionUser");
		}
		model.addAttribute("user",u);
		return "editprofile";
	}
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String perform_login(@RequestParam("email") String email, @RequestParam("pwd") String pwd, Model model, HttpSession session) {
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
	}
	@RequestMapping(value = "user_profile", method = RequestMethod.GET)
	public String display_user_profile(Model model, HttpSession session){
		UserUI u = new UserUI();
		UserEducation u_ed = new UserEducation();
		ProfileImage pi = new ProfileImage();
		UserPersonal up = new UserPersonal();
		System.out.println("controller");
		if(null==session.getAttribute("sessionUser")){
			return "redirect:"+"login";
		}else{
			u = (UserUI)session.getAttribute("sessionUser");
			UserEducationDal ed = new UserEducationDal();

			UserPersonalDal updal = new UserPersonalDal();			
			try {
				u_ed = ed.selectUserEducationById(u.getUserId());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try{
				up = updal.selectUserPersonalById(u.getUserId());
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		model.addAttribute("user",u);
		model.addAttribute("education",u_ed);
		model.addAttribute("personal",up);
		
		return "user_profile";
	}
	@RequestMapping(value = "displayimage", method = RequestMethod.GET)
	void display_image(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        System.out.println(id);
        int user_id = Integer.parseInt(id);
        ImageDal imd = new ImageDal();
        ProfileImage pi = null;
		try {
			pi = imd.getProfileImage(user_id);
			response.setContentType("image/jpeg");
            response.getOutputStream().write( pi.img);
            response.getOutputStream().close();
       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	@Autowired
    private JavaMailSender mailSender;
	@RequestMapping(value = "forgotpassword", method = RequestMethod.POST)
	public String forgot_password(@RequestParam("email") String email , Model model, HttpSession session){
		logger.info("Welcome login! The client locale is {}.");
		UserUI u = new UserUI();
		if(null==session.getAttribute("sessionUser")){
			UserDal ud = new UserDal();
			try {
				if(ud.checkIfUniqueEmail(email))
				{
					model.addAttribute("headerMessage","Check your inbox, A password reset link has been sent to "+email);
					SimpleMailMessage emailobj = new SimpleMailMessage();
			        emailobj.setTo(email);
			        emailobj.setSubject("Password Reset Link");
			        emailobj.setText("Hello");
			         
			        // sends the e-mail
			        mailSender.send(emailobj);
					return "forgotpassword";
				}
				else
				{
					model.addAttribute("headerMessage",email+"no such user exists");
					return "forgotpassword";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				model.addAttribute("headerMessage","something went wrong, please try again");
				return "forgotpassword";
			}
		}else{
			u = (UserUI)session.getAttribute("sessionUser");
			model.addAttribute("headerMessage","you are already logged in");
		}
		model.addAttribute("user",u);
		return "redirect:"+"logout";
	}
}
