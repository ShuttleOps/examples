package org.shuttleops.samplespringapp.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator{

	@Autowired
	private HealthToggle healthToggle;

	private final String message_key = "CustomHealthIndicator";

	@Override
	public Health health(){
		if( healthToggle.isHealthy() ){
			return Health.up().withDetail( message_key, "Available" ).build();
		}
		return Health.down().withDetail( message_key, "Not Available" ).build();
	}

}

