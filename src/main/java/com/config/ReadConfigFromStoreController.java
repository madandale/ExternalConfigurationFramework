package com.config;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.archaius.DefaultPropertyFactory;
import com.netflix.archaius.Layers;
import com.netflix.archaius.config.DefaultLayeredConfig;
import com.netflix.archaius.config.PollingDynamicConfig;
import com.netflix.archaius.config.polling.FixedPollingStrategy;
import com.netflix.archaius.readers.URLConfigReader;

@RestController
public class ReadConfigFromStoreController {
	
	DefaultLayeredConfig config = new DefaultLayeredConfig();
	
	ReadConfigFromStoreController() {
		
		config.addConfig(Layers.REMOTE, new PollingDynamicConfig(
				new URLConfigReader("file:///Users/madandale/Documents/Silenium/config/application.properties"), 
				new FixedPollingStrategy(1, TimeUnit.SECONDS)));
		
	}
	
	@GetMapping("/ReadConfigProperty")
	public String ReadConfigFile() {
		
		DefaultPropertyFactory factory = new DefaultPropertyFactory(config);
		
		System.out.print("application propery value == "+ factory.get("app.message",String.class).get());
		return factory.get("app.message",String.class).get();
	}

}
