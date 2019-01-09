package com.emp.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.emp.demo.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeProjectApplicationTests {
	
	private MockMvc mockMvc;
	
	ObjectMapper objMap=new ObjectMapper();
	
	@Autowired
	public WebApplicationContext context;
	
	@Before
	public void setUp() {
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void createEmployeeTest() throws Exception {
		Employee emp=new Employee();
		emp.setEmpId(1);
		emp.setEmpName("Rakesh");
		emp.setAge(30);
		emp.setAddress("Chennai");
		emp.setSalary(50000L);
		String jsonRequest=objMap.writeValueAsString(emp);
		MvcResult result=mockMvc.perform(post("/addEmp").content(jsonRequest).
				contentType(MediaType.APPLICATION_JSON_VALUE)).
				andExpect(status().isOk()).andReturn();
		String resultContent=result.getResponse().getContentAsString();
		@SuppressWarnings("unchecked")
		ResponseEntity<Object> response=objMap.readValue(resultContent, ResponseEntity.class);
		Assert.assertEquals(response.getStatusCodeValue(),200);
		
	}

	

}

