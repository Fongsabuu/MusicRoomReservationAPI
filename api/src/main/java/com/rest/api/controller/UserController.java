package com.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rest.api.model.User;
import com.rest.api.service.StorageService;
import com.rest.api.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {
	
	@Autowired  
    private UserService userService;  
	
	@Autowired
	StorageService storageService;
   
    @PostMapping("/")  
    private int addUser(@RequestBody User user) {  
//    	List<User> checkuser = this.userService.getUserByEmail(user);
//    	if(checkuser.size() == 0) {
//        	return userService.addUser(user);
//    	}else {
//    		return 0;
//    	}
    	return userService.addUser(user);
    }  
  
    @PutMapping("/")  
    private int updateUser(@RequestBody  User user) {  
        return userService.updateUser(user);
    }  
    
    @GetMapping("/")  
    private List<User> getAllUser() {  
        return userService.getAllUser();  
    } 
    
    @GetMapping("/{id}")  
    private List<User> getUserById(@PathVariable("id") int user_id) {  
        return userService.getUserById(user_id);  
    } 
    
    @PostMapping("/login")  
    private List<User> getUserByEmail(@RequestBody User user) {  
        return userService.getUserByEmail(user);  
    }  
   
    
    @PutMapping("/delete/{id}")  
    private int delete(@PathVariable("id") int id) {  
        return userService.deleteUser(id);  
    }  
    
    @PostMapping("/imguser/{id}")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable("id") int id) {
		String message = "";
		try {
			storageService.storeImgUser(file);
			message = "You successfully update img_user " + file.getOriginalFilename() + "!";
			int updatesuccess = userService.UpdateImgUser(id, file.getOriginalFilename());
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to update img_user " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
    
    @GetMapping("/imguser/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadImgUser(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
    
    @PostMapping("/updateuserstatus/")
    public int updateUserStatus(@RequestParam("user_id") String user_id, @RequestParam("user_status") String user_status) {
    	return userService.updateUserStatus(Integer.parseInt(user_id), user_status);
    }
}
