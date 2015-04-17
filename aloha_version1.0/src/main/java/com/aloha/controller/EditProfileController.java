package com.aloha.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
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
	/*protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {

        FileUploadBean bean = (FileUploadBean) command;
        byte[] file = bean.getFile();
        if (file == null) {
             // hmm, that's strange, the user did not upload anything
        	FileOutputStream fos = new FileOutputStream("E://some.out");
        	fos.write(file);
        }
        
        return super.onSubmit(request, response, command, errors);
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
        throws ServletException {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        
    }*/

}
