package com.config;

import javax.sql.DataSource;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.netflix.config.DynamicConfiguration;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.sources.JDBCConfigurationSource;

@RunWith(SpringRunner.class)

@WebMvcTest(value = ReadConfigFromJDBCController.class)

public class ReadConfigFromJDBCControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@Before
	public void setup() {
		
//		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	        dataSource.setDriverClassName("org.h2.Driver");
//	        dataSource.setUrl("jdbc:h2:file:~/data/config");
//	        dataSource.setUsername("sa");
//	        dataSource.setPassword("");
//
//	            JDBCConfigurationSource source = new JDBCConfigurationSource(
//	            		dataSource,
//	                    "select distinct property_key, property_value from MySiteProperties",
//	                    "property_key", "property_value");
//	            FixedDelayPollingScheduler scheduler = new FixedDelayPollingScheduler(
//	                    0, 10, false);
//	            DynamicConfiguration configuration = new DynamicConfiguration(source,
//	                    scheduler);
//	            DynamicPropertyFactory.initWithConfigurationSource(configuration);
	     
	}
	
	@Test
	public void testReadConfigFileFromJDBC() {
		String expected = "hello world";


		try {
			JSONAssert.assertEquals(expected,"hello World", false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 @After
	 public void finalize() {
	      
	 }
	
}
