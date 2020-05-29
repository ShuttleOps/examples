package org.shuttleops.samplespringapp.controllers;

import org.shuttleops.samplespringapp.health.HealthToggle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController{

	@Autowired
	private Environment env;

	@Autowired
	HealthToggle healthToggle;

	@Value("${application.ipv4}")
	private String ipv4;

	@GetMapping( "/" )
	public String index(){
		return "index";
	}

	@GetMapping( "/ipv4" )
	public String ipv4( Model model ){
		model.addAttribute( "message", String.format( "The IPv4 address of this instance is: %s", ipv4) );
		return "message";
	}

	@GetMapping( "/make-unhealthy" )
	public String makeUnhealthy( Model model ){
		int unhealthySeconds = Integer.parseInt( env.getProperty( "application.unhealthy_seconds" ) );
		healthToggle.setUnhealthy( unhealthySeconds );
		model.addAttribute( "message", String.format( "Health is now set to 'unhealthy' for %d seconds (IPv4: %s).", unhealthySeconds, ipv4 ) );
		return "message";
	}
}
