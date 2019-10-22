package com.config;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.netflix.archaius.Layers;
import com.netflix.archaius.config.DefaultLayeredConfig;
import com.netflix.archaius.config.PollingDynamicConfig;
import com.netflix.archaius.config.polling.FixedPollingStrategy;
import com.netflix.archaius.readers.URLConfigReader;


	@RunWith(SpringRunner.class)

	@WebMvcTest(value = ReadConfigFromStoreController.class)
	public class ReadLogConfigFromStoreControllerTest {


		
		@Autowired
		private MockMvc mockMvc;
		
		

		@Before
		public void setup() {
			DefaultLayeredConfig config1 = new DefaultLayeredConfig();

			config1.addConfig(Layers.REMOTE, new PollingDynamicConfig(
					new URLConfigReader("file:///Users/madandale/Documents/Silenium/config/log.properties"), 
					new FixedPollingStrategy(1, TimeUnit.SECONDS)));
		}
		
		@Test
		public void testReadLogConfigFile() {
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/ReadLogConfigProperty").accept(
					MediaType.APPLICATION_JSON);

			try {
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				
				String expected = "This is the log property data";


				Assert.assertEquals(result.getResponse().getContentAsString(), expected);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		
		
	}

