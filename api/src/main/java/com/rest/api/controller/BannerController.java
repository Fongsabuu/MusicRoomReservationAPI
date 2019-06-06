package com.rest.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rest.api.service.StorageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/banner")
public class BannerController {

	@Autowired  
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	StorageService storageService;
	
	@PostMapping("/")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.storeImgBanner(file);
			message = "You successfully update img_user " + file.getOriginalFilename() + "!";
			String sql = "INSERT INTO banner (img_name, alt_name) VALUES ('"+file.getOriginalFilename()+"','"+file.getOriginalFilename()+"')";
			this.jdbcTemplate.update(sql);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to update img_user " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@GetMapping("/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadImgBanner(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@GetMapping("/")
	private List<Map<String, Object>> getBanner() {
		String sql = "SELECT * FROM banner";
		return jdbcTemplate.queryForList(sql);
	}
}
