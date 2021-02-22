package com.crmiguez.aixinainventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories("com.crmiguez.aixinainventory.repository")
@EntityScan("com.crmiguez.aixinainventory.entities")
public class AixinainventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AixinainventoryApplication.class, args);
    }

}
