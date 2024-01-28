package com.stockapp.eureka;

import java.io.IOException;
import java.net.URI;

import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.BootstrapRegistryInitializer;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Podemos agregar credenciales para acceder a Config Server directamente en la url: configserver:http://configUser:configPassword@localhost:8071
 * O podemos hacer lo que tenemos aca, un filtro que agrega el Authorization header
 * 
 * Esta clase afectará todos los RestTemplate que se inicialicen al INICIARSE la app, solo en ese momento.
 * It affects all RestTemplate instances created during the bootstrap phase
 */
public class CustomBootstrapRegistryInitializer implements BootstrapRegistryInitializer {

	@Override
	public void initialize(BootstrapRegistry registry) {
		registry.register(RestTemplate.class, context -> {
			RestTemplate restTemplate = new RestTemplate();
			// Customize RestTemplate here
			
			System.out.println("HOLA HOLAAAA");
			
			restTemplate.setRequestFactory(customClientHttpRequestFactory());
			
			return restTemplate;
		});
	}
	
	
	private ClientHttpRequestFactory customClientHttpRequestFactory() {
        return new MyCustomClientHttpRequestFactory();
    }

    private static class MyCustomClientHttpRequestFactory extends SimpleClientHttpRequestFactory {

        @Override
        public ClientHttpRequest createRequest(URI uri, org.springframework.http.HttpMethod httpMethod) throws IOException {
            ClientHttpRequest request = super.createRequest(uri, httpMethod);
            
            // Add custom headers here
            
            request.getHeaders().setBasicAuth("configUser", "configPassword");
            
            //request.getHeaders().add("Custom-Header", "Custom-Value");

            return request;
        }
    }
	
}
