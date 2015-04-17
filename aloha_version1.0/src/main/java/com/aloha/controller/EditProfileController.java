package com.aloha.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.aloha.common.util.FileUploadBean;

@SuppressWarnings("deprecation")
@Controller
public class EditProfileController{
	
	@RequestMapping(value = "uploadform", method = RequestMethod.POST)
	public String save(FileUploadBean uploadForm, Model map) throws IOException {
         
        MultipartFile file = uploadForm.getFile();
        byte[] bytefile;
        if (file != null) {
        	
        	System.out.println("uploaded");
        	bytefile = file.getBytes();
        	FileOutputStream fos = new FileOutputStream("E://iu acad//some.jpg");
        	fos.write(bytefile);
       	 	map.addAttribute("headerMessage","success");
       	 }
       	 else{
       		System.out.println(" not uploaded");
       		map.addAttribute("headerMessage","failed to upload file");
       }

        return "user_profile";
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
