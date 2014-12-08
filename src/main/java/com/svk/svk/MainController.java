package com.svk.svk;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.svk.svk.model.KitchenImage;
import com.svk.svk.model.Member;
import com.svk.svk.service.KitchenImageService;
import com.svk.svk.service.MemberService;
 
@Controller
public class MainController {
 
	private MemberService memberService;
	private KitchenImageService kitchenImageService;
	
	@Autowired(required=true)
	@Qualifier(value="memberService")
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Autowired(required=true)
	@Qualifier(value="kitchenImageService")
	public void setKitchenImageService(KitchenImageService kitchenImageService) {
		this.kitchenImageService = kitchenImageService;
	}
	
	private Member getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			return memberService.getMemberByEmailId(userDetail.getUsername());
		}
		return null;
	}
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
 
	  ModelAndView model = new ModelAndView();
	  model.addObject("title", "Spring Security Login Form - Database Authentication");
	  model.addObject("message", "This is default page!");
	  Member m = getLoggedInUser();
	  if (m != null) {
		  model.addObject("fullName", m.getFirstName() + " " + m.getLastName());
	  }
	  model.setViewName("hello");
	  return model;
 
	}
 
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {
 
	  ModelAndView model = new ModelAndView();
	  model.addObject("title", "Spring Security Login Form - Database Authentication");
	  model.addObject("message", "This page is for ROLE_ADMIN only!");
	  model.setViewName("admin");
	  return model;
 
	}
 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
 
	  ModelAndView model = new ModelAndView();
	  if (error != null) {
		model.addObject("error", "Invalid username and password!");
	  }
 
	  if (logout != null) {
		model.addObject("msg", "You've been logged out successfully.");
	  }
	  model.setViewName("login");
 
	  return model;
 
	}
 
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
 
	  ModelAndView model = new ModelAndView();
 
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }
 
	  model.setViewName("403");
	  return model;
 
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
    public ModelAndView provideUploadInfo() {
		ModelAndView model = new ModelAndView();
		model.setViewName("image_upload");
        return model;
    }
 
	@RequestMapping(value="/upload2", method=RequestMethod.POST)
    public ModelAndView handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file){
		ModelAndView model = new ModelAndView();
		if (!file.isEmpty()) {
			byte[] imageBytes;
			try {
				imageBytes = file.getBytes();
				Member m = getLoggedInUser();
				KitchenImage ki = new KitchenImage();
				ki.setImage(imageBytes);
				ki.setMemberId(m.getMemberId());
				ki.setImageIndex(1);
				kitchenImageService.addKitchenImage(ki);
			} catch (IOException e) {
				e.printStackTrace();
			}
			model.addObject("filename", file.getName());
		}
		
		
		model.setViewName("image_upload");
		return model;
	}
}
