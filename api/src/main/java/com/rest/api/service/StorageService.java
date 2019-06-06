package com.rest.api.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation_imguser = Paths.get("src/main/resources/static/img_user");
	private final Path rootLocation_imgroom = Paths.get("src/main/resources/static/imgroom");
	private final Path rootLocation_banner = Paths.get("src/main/resources/static/banner");
	private final Path rootLocation_payment = Paths.get("src/main/resources/static/payment");
	private final Path rootLocation_bill = Paths.get("src/main/resources/static/bill");

	public void storeImgUser(MultipartFile file) {
		try {
			Path checkfile = this.rootLocation_imguser.resolve(file.getOriginalFilename());
			Resource resource = new UrlResource(checkfile.toUri());
			System.out.println(checkfile.toUri());
			System.out.println(resource);
			if (resource.exists() || resource.isReadable()) {
				System.out.println("don't store file");
			} else {
				System.out.println("stored file");
				Files.copy(file.getInputStream(), this.rootLocation_imguser.resolve(file.getOriginalFilename()));
			}
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public Resource loadImgUser(String filename) {
		try {
			Path file = rootLocation_imguser.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public void storeImgRoom(MultipartFile file) {
		try {
			Path checkfile = this.rootLocation_imgroom.resolve(file.getOriginalFilename());
			Resource resource = new UrlResource(checkfile.toUri());
			if (resource.exists() || resource.isReadable()) {
				System.out.println("don't store file");
			} else {
				System.out.println("stored file");
				Files.copy(file.getInputStream(), this.rootLocation_imgroom.resolve(file.getOriginalFilename()));
			}
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public Resource loadImgRoom(String filename) {
		try {
			Path file = rootLocation_imgroom.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
	
	public void storeImgBanner(MultipartFile file) {
		try {
			Path checkfile = this.rootLocation_banner.resolve(file.getOriginalFilename());
			Resource resource = new UrlResource(checkfile.toUri());
			if (resource.exists() || resource.isReadable()) {
				System.out.println("don't store file");
			} else {
				System.out.println("stored file");
				Files.copy(file.getInputStream(), this.rootLocation_banner.resolve(file.getOriginalFilename()));
			}
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public Resource loadImgBanner(String filename) {
		try {
			Path file = rootLocation_banner.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
	
	public void storeImgPayment(MultipartFile file) {
		try {
			Path checkfile = this.rootLocation_payment.resolve(file.getOriginalFilename());
			Resource resource = new UrlResource(checkfile.toUri());
			if (resource.exists() || resource.isReadable()) {
				System.out.println("don't store file");
			} else {
				System.out.println("stored file");
				Files.copy(file.getInputStream(), this.rootLocation_payment.resolve(file.getOriginalFilename()));
			}
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public Resource loadImgPayment(String filename) {
		try {
			Path file = rootLocation_payment.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
	
	public Resource loadBill(String filename) {
		try {
			Path file = rootLocation_bill.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}

//	public void deleteAll() {
//		FileSystemUtils.deleteRecursively(rootLocation.toFile());
//	}
//
//	public void init() {
//		try {
//			Files.createDirectory(rootLocation);
//		} catch (IOException e) {
//			throw new RuntimeException("Could not initialize storage!");
//		}
//	}
}