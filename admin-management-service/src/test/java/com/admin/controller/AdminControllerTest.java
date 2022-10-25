package com.admin.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.admin.pojo.Admin;
import com.admin.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private AdminController adminController;
	
	@Mock
	private AdminService adminService;
	private Admin admin;
	private Optional<Admin> optionalAdmin;
	private List<Admin> admins;
	
	@BeforeEach
	public void setUp() {
		
		admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("suma");
		admin.setLastName("palagiri");
		admin.setPhone(987654321);
		admin.setLocation("nandyal");
		admin.setUserName("suma");
		admin.setEmail("suma@mail.com");
		admin.setPassword("123");
		
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
	}
	
	@Test
	public void testAddAdmin() throws Exception {
		
		when(adminService.saveAdmin(any())).thenReturn(admin);
		mockMvc.perform(post("/admin/add").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(admin))).andExpect(status().isOk());
		verify(adminService, times(1)).saveAdmin(any());
		
	}
	
	@Test
	public void testGetAllAdmin() throws Exception {
		
		when(adminService.getAllAdmin()).thenReturn(admins);
		mockMvc.perform(get("/admin/all").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(admins))).andDo(print());
		verify(adminService,times(1)).getAllAdmin();
	}
	
	@Test
	public void testGetAdminById() throws Exception {
		
		when(adminService.getAdminById(10)).thenReturn(admin);
		mockMvc.perform(get("/admin/find/10").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(admin))).andDo(print());
		verify(adminService,times(1)).getAdminById(10);
	}
	
	@Test
	public void testUpdateAdmin() throws Exception {
		
		when(adminService.updateAdmin(any())).thenReturn(admin);
		mockMvc.perform(put("/admin/update").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(admin))).andExpect(status().isOk());
		verify(adminService,times(1)).updateAdmin(any());
	}
	
	@Test
	public void testDeleteAdminById() throws Exception {
		
		mockMvc.perform(delete("/admin/delete/10").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(admin))).andExpect(status().isOk());
		verify(adminService,times(1)).deleteAdminById(10);
	}
	
	@Test
	public void testGetAdminByUserName() throws Exception {
		
		when(adminService.getAllAdminByUserName("suma")).thenReturn(optionalAdmin);
		mockMvc.perform(get("/admin/byusername/suma").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(optionalAdmin))).andDo(print());
		verify(adminService,times(1)).getAllAdminByUserName("suma");
	}
	
	@Test
	public void testGetAdminByPhone() throws Exception {
		
		when(adminService.getAllAdminByPhone(987654321)).thenReturn(optionalAdmin);
		mockMvc.perform(get("/admin/byphone/987654321").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(optionalAdmin))).andDo(print());
		verify(adminService, times(1)).getAllAdminByPhone(987654321);
	}
	
	@Test
	public void testGetAdminByLocation() throws Exception {
		
		when(adminService.getAllAdminByLocation("Nandyal")).thenReturn(admins);
		mockMvc.perform(get("/admin/bylocation/Nandyal").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(admins))).andDo(print());
		verify(adminService,times(1)).getAllAdminByLocation("Nandyal");
	}
	
	@Test
	public void testDoLogin() throws Exception {
		
		when(adminService.doLogin("suma", "123")).thenReturn(admin);
		mockMvc.perform(post("/admin/login").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(admin))).andDo(print());
		verify(adminService,times(1)).doLogin("suma", "123");
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper(); 
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		}
		catch (Exception exception) {
			throw new RuntimeException(exception); 
		}
	}

}
