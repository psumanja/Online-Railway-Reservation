package com.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.admin.pojo.Admin;

@SpringBootTest
public class AdminRepositoryTest {
	
	@Mock
	private AdminRepository adminRepository;
	
	@Test
	public void testSaveAdmin() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("suma");
		admin.setLastName("palagiri");
		admin.setPhone(987654321);
		admin.setLocation("nandyal");
		admin.setUserName("suma");
		admin.setEmail("suma@mail.com");
		admin.setPassword("123");
		
		when(adminRepository.save(admin)).thenReturn(admin);
		assertEquals("suma", admin.getUserName());
	}
	
	@Test
	public void testFindAllAdmin() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("suma");
		admin.setLastName("palagiri");
		admin.setPhone(987654321);
		admin.setLocation("nandyal");
		admin.setUserName("suma");
		admin.setEmail("suma@mail.com");
		admin.setPassword("123");
		
		Admin admin1 = new Admin();
		admin1.setAdminId(11);
		admin1.setFirstName("sirisha");
		admin1.setLastName("b");
		admin1.setPhone(123456789);
		admin1.setLocation("kurnool");
		admin1.setUserName("siri");
		admin1.setEmail("siri@mail.com");
		admin1.setPassword("456");
		
		Admin admin2 = new Admin();
		admin2.setAdminId(12);
		admin2.setFirstName("james");
		admin2.setLastName("smith");
		admin2.setPhone(987654345);
		admin2.setLocation("hyderabad");
		admin2.setUserName("james");
		admin2.setEmail("james@mail.com");
		admin2.setPassword("789");
		
		List<Admin> allAdmins = new ArrayList<>();
		allAdmins.add(admin);
		allAdmins.add(admin1);
		allAdmins.add(admin2);
		
		when(adminRepository.findAll()).thenReturn(allAdmins);
		assertEquals(3, allAdmins.size());
	}
	
	@Test
	public void testFindAdminById() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("suma");
		admin.setLastName("palagiri");
		admin.setPhone(987654321);
		admin.setLocation("nandyal");
		admin.setUserName("suma");
		admin.setEmail("suma@mail.com");
		admin.setPassword("123");
		
		Optional<Admin> optionalAdmin = Optional.of(admin);
		when(adminRepository.findById(10)).thenReturn(optionalAdmin);
		assertEquals("suma", admin.getUserName());
	}
	
	@Test
	public void testLogin() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("suma");
		admin.setLastName("palagiri");
		admin.setPhone(987654321);
		admin.setLocation("nandyal");
		admin.setUserName("suma");
		admin.setEmail("suma@mail.com");
		admin.setPassword("123");
		
		when(adminRepository.login("suma", "123")).thenReturn(admin);
		assertEquals("suma", admin.getUserName());
	}

}