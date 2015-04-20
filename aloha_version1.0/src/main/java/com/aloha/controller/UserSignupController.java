package com.aloha.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.entities.user.User;
import com.aloha.common.sal.SignupService;
import com.aloha.common.util.Secure_Hash;

@Controller
public class UserSignupController extends Secure_Hash{
private static final Logger logger = LoggerFactory.getLogger(UserSignupController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "login/sign_up", method = RequestMethod.GET)
	public String sign_up(Locale locale, Model model) {
		logger.info("Welcome Sign_up! The client locale is {}.", locale);
		
		return "sign_up";
	}
	@RequestMapping(value = "login/sign_up", method = RequestMethod.POST)
	public String perform_sign_up(@RequestParam("fname") String fname,@RequestParam("lname") String lname, @RequestParam("cnum") String cnum, @RequestParam("email") String email, @RequestParam("dob") Date dob, @RequestParam("pwd") String pwd,@RequestParam("cpwd") String cpwd, Model model) {
		logger.info("Welcome Sign_up! The client locale is {}.");
		SignupService signupService = new SignupService();
		int res = signupService.perform_sign_up(fname, lname, cnum, email, dob, pwd, cpwd);
		if(res==0)
		{
			((Model) model).addAttribute("headerMessage","Something went wrong");
			return "sign_up";
		}
		else if(res==1){
			((Model) model).addAttribute("headerMessage","Please login, you have successfully signed up");
			//System.out.println("success");
		}
		else if(res==-1)
			((Model) model).addAttribute("headerMessage","Email already exists");
		return "Login";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
