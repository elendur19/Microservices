package hr.rasus.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv4Stack" , "true");
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
