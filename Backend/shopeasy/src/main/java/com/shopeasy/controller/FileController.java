package com.shopeasy.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopeasy.service.FileService;

@RestController
@RequestMapping("/imageController")
public class FileController {
	
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/")
	public String sayWelcome() {
		return "Welcome to shopeasy";
	}
	
	@Value("${project.image}")
	private String path;
	
//	image upload 
	@PostMapping(value="/imageupload",produces = {MediaType.ALL_VALUE,"application/json"})
	public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image )throws IOException{
		
		String fileName = this.fileService.uploadImage(path, image);
//		I need to set vendor image and save image name in database 
		
		return new ResponseEntity<String>(fileName,HttpStatus.OK);
	}
	
//	getImage
	@GetMapping(value="/getimage/{imageName}",produces = MediaType.ALL_VALUE)
	public void getImage(@PathVariable("imageName") String imageName,
			              HttpServletResponse response)throws IOException{
		
		InputStream resourse = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.ALL_VALUE);
         StreamUtils.copy(resourse, response.getOutputStream());
	}

}
