package com.gcaa.nm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages={"com.gcaa.nm",})
@EnableScheduling
/*@ImportResource({"classpath:\\META-INF\\spring\\jms\\jms-init-context.xml"})*/
public class NmConnectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(NmConnectorApplication.class, args);
	}
}
