
package com.gcaa.nm.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.HttpsUrlConnectionMessageSender;

import com.gcaa.nm.manager.impl.NmSubscriptionManagerImpl;
import com.gcaa.nm.wss.client.NmSubscriptionClientImpl;

@Configuration
public class NmSubscriptionConfiguration {

	@Value("${gcaa.nm.endpoint-url}")
	private String url;

	@Value("${gcaa.security.ssl.key-store}")
	private File keyStore;

	@Value("${gcaa.security.ssl.key-store-password}")
	private String keyStorePassword;

	@Value("${gcaa.security.ssl.trust-store}")
	private File trustStore;

	@Value("${gcaa.security.ssl.trust-store-password}")
	private String trustStorePassword;

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
	public NmSubscriptionClientImpl quoteClient(Jaxb2Marshaller marshaller,
			HttpsUrlConnectionMessageSender httpsUrlConnectionMessageSender) {
		NmSubscriptionClientImpl client = new NmSubscriptionClientImpl();
		client.setDefaultUri(url/* "https://www.b2b.preops.nm.eurocontrol.int/B2B_PREOPS/gateway/spec/20.5.0" */);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		client.setMessageSender(httpsUrlConnectionMessageSender);
		return client;
	}

	@Bean
	public HttpsUrlConnectionMessageSender httpsUrlConnectionMessageSender() throws NoSuchAlgorithmException,
			CertificateException, IOException, KeyStoreException, UnrecoverableKeyException {

		HttpsUrlConnectionMessageSender messageSender = new HttpsUrlConnectionMessageSender();
		
		messageSender.setKeyManagers(getKeyManagerFactory().getKeyManagers());
		messageSender.setTrustManagers(getTrustManagerFactory().getTrustManagers());

		// otherwise: java.security.cert.CertificateException: No name matching
		// localhost found
		/*messageSender.setHostnameVerifier((hostname, sslSession) -> {
			 return true;
		});
*/
		return messageSender;

	}

	private KeyManagerFactory getKeyManagerFactory() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException, UnrecoverableKeyException {
		KeyStore ks;

		ks = KeyStore.getInstance("JKS");

		ks.load(new FileInputStream(keyStore), keyStorePassword.toCharArray());

		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(ks, keyStorePassword.toCharArray());
		
		return keyManagerFactory;
	}
	
	private TrustManagerFactory getTrustManagerFactory() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
		KeyStore ts = KeyStore.getInstance("JKS");
		ts.load(new FileInputStream(trustStore), trustStorePassword.toCharArray());

		TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(ts);
		
		return trustManagerFactory;
	}
}
