package org.shuttleops.samplespringapp.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Date;

@Component
public class HealthToggle{

	@Autowired
	private TaskScheduler taskScheduler;

	private boolean healthy = true;

	public boolean isHealthy(){
		return healthy;
	}

	public void setUnhealthy( int seconds ){
		this.healthy = false;
		taskScheduler.schedule( () -> {
			this.healthy = true;
		}, new Date( OffsetDateTime.now().plusSeconds( seconds ).toInstant().toEpochMilli() ) );
	}
}
