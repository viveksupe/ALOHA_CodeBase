package com.aloha.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.aloha.common.dao_manager.dal.ImageDal;
import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.entities.user.User;
import com.aloha.common.model.UserUI;
import com.aloha.common.util.FileUploadBean;
import com.aloha.common.util.ProfileImage;

@SuppressWarnings("deprecation")
@Controller
@SessionAttributes("sessionUser")
public class EditProfileController{
	
	@RequestMapping(value = "uploadform", method = RequestMethod.POST)
	public String save(FileUploadBean uploadForm, Model map, HttpSession session) {
		UserUI u = new UserUI();
		if(null==session.getAttribute("sessionUser")){
			return "Login";
		}else{
			u = (UserUI)session.getAttribute("sessionUser");
			map.addAttribute("user",u);
			MultipartFile file = uploadForm.getFile();
	        byte[] bytefile;
	        ProfileImage pi = null;
	        ImageDal pdal = new ImageDal();
	        if (file != null) {
	        	System.out.println("uploaded");
	        	try {
	        		bytefile = file.getBytes();
	        		pi = pdal.getProfileImage(u.getUserId());
		        	if(pi!=null)
		        	{
		        		pdal.modifyImage(u.getUserId(), bytefile, pi.getImg_id());
		        		pi.setImg(bytefile);
		        	}
		        	else
		        		pi = pdal.insertImage(u.getUserId(), bytefile);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.addAttribute("headerMessage","failed to upload file");
					return "redirect:"+"user_profile";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.addAttribute("headerMessage","failed to upload file");
					return "redirect:"+"user_profile";
				}
	        	  
	       	 	map.addAttribute("headerMessage","success");
	       	 }
	       	 else{
	       		System.out.println(" not uploaded");
	       		map.addAttribute("headerMessage","failed to upload file");
	       }
	        map.addAttribute("pi",pi);
	        return "redirect:"+"user_profile";
		}
		  
	        
    }
	@RequestMapping(value = "editaccountdetails", method = RequestMethod.GET)
	public String show_page(Model map,HttpSession session){
		UserUI u = new UserUI();
		if(null==session.getAttribute("sessionUser")){
			return "Login";
		}
		else
		{
			u = (UserUI)session.getAttribute("sessionUser");
		}
		map.addAttribute("user",u);
		return "editaccountdetails";
	}
	@RequestMapping(value = "editaccountdetails", method = RequestMethod.POST)
	public String save_account_details(@RequestParam("fname") String fname,@RequestParam("lname") String lname, @RequestParam("cnum") String cnum, @RequestParam("dob") Date dob, Model map, HttpSession session) {
		UserUI u = new UserUI();
		User update_u = new User();
		UserDal ud = new UserDal();
		if(null==session.getAttribute("sessionUser")){
			return "Login";
		}else{
			u = (UserUI)session.getAttribute("sessionUser");
			u.setFirstName(fname);
			u.setLastName(lname);
			u.setContactNumber(cnum);		
			u.setDateOfBirth(dob);
			update_u.updatefromUI(u);
			int res =0;
			try {
				res = ud.updateAccountInfo(update_u);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				((Model) map).addAttribute("headerMessage","Something went wrong");
				return "redirect"+"user_profile";
			}
			if(res==1)
				((Model) map).addAttribute("headerMessage","you have successfully updated");
			//System.out.println("success");
			map.addAttribute("user",u);
		}
	    return "redirect:"+"user_profile";		
	}
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
