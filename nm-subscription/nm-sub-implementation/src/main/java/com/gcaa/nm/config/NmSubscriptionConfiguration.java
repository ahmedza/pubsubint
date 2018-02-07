
package com.gcaa.nm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.gcaa.nm.wss.client.NmSubscriptionClientImpl;

@Configuration
public class NmSubscriptionConfiguration {

	@Value("${gcaa.nm.endpoint-url}")
	private String url;

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage>
		// specified in
		// pom.xml
		marshaller.setContextPath("com.gcaa.nm.eurocontrol._2_5_0");
		return marshaller;
	}

	@Bean
	public NmSubscriptionClientImpl nmClient(Jaxb2Marshaller marshaller) {
		NmSubscriptionClientImpl client = new NmSubscriptionClientImpl();
		client.setDefaultUri(url);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);

		return client;
	}
}
