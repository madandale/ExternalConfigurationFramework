package com.config;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.archaius.DefaultPropertyFactory;
import com.netflix.archaius.Layers;
import com.netflix.archaius.config.DefaultLayeredConfig;
import com.netflix.archaius.config.PollingDynamicConfig;
import com.netflix.archaius.config.polling.FixedPollingStrategy;
import com.netflix.archaius.readers.URLConfigReader;

@RestController
public class ReadLogConfigFromStoreController {

	
		DefaultLayeredConfig config = new DefaultLayeredConfig();
		final Logger logger = LoggerFactory.getLogger(ReadLogConfigFromStoreController.class);

		ReadLogConfigFromStoreController() {
			logger.info("initialize the config class");
			config.addConfig(Layers.REMOTE, new PollingDynamicConfig(
					new URLConfigReader("file:///Users/madandale/Documents/Silenium/config/property.log"), 
					new FixedPollingStrategy(1, TimeUnit.SECONDS)));
			logger.error("null data getting");

			logger.info("initialization of config class done");

			logger.warn("warning data getting");

		}
		
		@GetMapping("/ReadLogConfigProperty")
		public String ReadLogConfigProperties() {
			
			logger.info("ReadLogConfigProperty called");

			DefaultPropertyFactory factory = new DefaultPropertyFactory(config);
			
			System.out.print("application propery value == "+ factory.get("app.message",String.class).get());
			return factory.get("app.message",String.class).get();
		}

	}
