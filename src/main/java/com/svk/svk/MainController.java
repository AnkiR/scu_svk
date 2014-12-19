package com.svk.svk;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.svk.svk.model.KitchenImage;
import com.svk.svk.model.Member;
import com.svk.svk.service.KitchenImageService;
import com.svk.svk.service.MemberService;

@Controller
public class MainController {

	private MemberService memberService;
	private KitchenImageService kitchenImageService;

	@Autowired(required = true)
	@Qualifier(value = "memberService")
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Autowired(required = true)
	@Qualifier(value = "kitchenImageService")
	public void setKitchenImageService(KitchenImageService kitchenImageService) {
		this.kitchenImageService = kitchenImageService;
	}

	private Member getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			return memberService.getMemberByEmailId(userDetail.getUsername());
		}
		return null;
	}

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		// model.addObject("title",
		// "Spring Security Login Form - Database Authentication");
		// model.addObject("message", "This is default page!");
		Member m = getLoggedInUser();
		if (m != null) {
			model.addObject("fullName",
					m.getFirstName() + " " + m.getLastName());
		}
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title",
				"Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");
		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
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

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("403");
		return model;

	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView provideUploadInfo() {
		ModelAndView model = new ModelAndView();
		Member m = getLoggedInUser();
		if (m != null) {
			model.addObject("fullName",
					m.getFirstName() + " " + m.getLastName());
		}
		model.setViewName("image_upload");
		return model;
	}
	@RequestMapping(value="/instructions", method=RequestMethod.GET)
    public ModelAndView provideInstructionsInfo() {
		ModelAndView model = new ModelAndView();
		model.setViewName("uploadinstructions");
        return model;
    }



	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ModelAndView uploadFileHandler(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam CommonsMultipartFile[] fileUpload) {
		if (fileUpload != null && fileUpload.length > 0) {
			Member m = getLoggedInUser();

			int i = 0;
			kitchenImageService.deleteAllImagesForMember(m);
			for (CommonsMultipartFile aFile : fileUpload) {
				if (aFile == null || aFile.getBytes() == null
						|| aFile.getBytes().length < 1000) {
					continue;
				}
				KitchenImage ki = new KitchenImage();
				ki.setImage(aFile.getBytes());
				ki.setMemberId(m.getMemberId());
				ki.setImageIndex(i++);
				kitchenImageService.addKitchenImage(ki);
			}
			ModelAndView model = new ModelAndView();

			if (m != null) {
				model.addObject("fullName",
						m.getFirstName() + " " + m.getLastName());
			}
			dumpImages(request, response, m);
			model.setViewName("upload_success");
			return viewImage();
		}

		return null;
	}

	@RequestMapping(value = "/viewImage", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView viewImage() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ModelAndView model = new ModelAndView();
		try {
			Member m = getLoggedInUser();
			String filename = "member" + m.getMemberId() + ".png";
			File imgFile = new File("/temp/images", filename);

			IOUtils.copy(new FileInputStream(imgFile), out);

			byte[] encoded = Base64.encodeBase64(out.toByteArray());
			String encodedString = new String(encoded);
			model.addObject("image_src", encodedString);
			
			if (m != null) {
				model.addObject("fullName",
						m.getFirstName() + " " + m.getLastName());
			}
			model.setViewName("upload_success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	private byte[] dumpImages(HttpServletRequest request,
			HttpServletResponse response, Member m) {
		List<KitchenImage> ki = kitchenImageService
				.getKitchenImageByMember(getLoggedInUser());

		try {
			int i = 0;
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(ki.get(0)
					.getImage()));
			while (i < ki.size() - 1) {
				i++;
				BufferedImage img2 = ImageIO.read(new ByteArrayInputStream(ki
						.get(i).getImage()));
				img = joinBufferedImage(img, img2);
			}
			String filename = "member" + m.getMemberId() + ".png";
			File imgFile = new File("/temp/images", filename);
			System.out.println("Img file" + request.getContextPath());

			ImageIO.write(img, "png", imgFile);

			return null;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private BufferedImage joinBufferedImage(BufferedImage img1,
			BufferedImage img2) {
		// do some calculate first
		int offset = 0;
		int wid = img1.getWidth() + img2.getWidth() + offset;
		int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
		// create a new buffer and draw two image into the new image
		BufferedImage newImage = new BufferedImage(wid, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = newImage.createGraphics();
		Color oldColor = g2.getColor();
		// fill background
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, wid, height);
		// draw image
		g2.setColor(oldColor);
		g2.drawImage(img1, null, 0, 0);
		g2.drawImage(img2, null, img1.getWidth() + offset, 0);
		g2.dispose();
		return newImage;
	}
}
