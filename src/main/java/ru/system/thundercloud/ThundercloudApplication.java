package ru.system.thundercloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class ThundercloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThundercloudApplication.class, args);
	}

}
