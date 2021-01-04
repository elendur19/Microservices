package hr.rasus.microservice.humidity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HumidityMicroserviceApplication {

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack" , "true");
        SpringApplication.run(HumidityMicroserviceApplication.class, args);
    }

}
