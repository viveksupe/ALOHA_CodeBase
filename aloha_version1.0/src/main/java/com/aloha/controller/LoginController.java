package com.aloha.controller;

import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.entities.user.User;
import com.aloha.common.util.Secure_Hash;

@Controller
@SessionAttributes("sessionUser")
public class LoginController extends Secure_Hash{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String Login(Locale locale, Model model) {
		logger.info("Welcome login! The client locale is {}.", locale);
		
		return "Login";
	}
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String perform_login(@RequestParam("email") String email, @RequestParam("pwd") String pwd, Model model) {
		logger.info("Welcome login! The client locale is {}.");
		UserDal ud = new UserDal();
		User res= null;
		String ret = "Login";
		/*String hashed_pwd = "";
		try {
			hashed_pwd = getHash(pwd);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			model.addAttribute("headerMessage","Something went wrong please try again");
		}*/
		try {
			 //res = ud.getPasswordByEmail(email,hashed_pwd);
			res = ud.getPasswordByEmail(email,pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			model.addAttribute("headerMessage","Something went wrong please try again");
		}
		finally{
			
		}
		if(res!=null)	
		{
			model.addAttribute("sessionUser",res);
			return "redirect:"+"user_profile";
			
		}
		else
			model.addAttribute("headerMessage","email or password does not match please try again");
		return ret;
	}
	@RequestMapping(value = "user_profile", method = RequestMethod.GET)
	public String display_user_profile(Locale locale, Model model, HttpSession session){
		logger.info("Welcome login! The client locale is {}.", locale);
		User u = new User();
		if(null==session.getAttribute("sessionUser")){
			return "redirect:"+"login";
		}else{
			u = (User)session.getAttribute("sessionUser");
		}
		model.addAttribute("user",u);
		return "user_profile";
	}
}
