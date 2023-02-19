package com.shopeasy.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	public String uploadImage (String path,MultipartFile file) throws IOException;
	
	public InputStream getResource(String path,String fileName) throws FileNotFoundException;

}
