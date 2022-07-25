package nts.sixblack.mainserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MainServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainServeApplication.class, args);
    }

}
