package org.drule.javadocker;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {
	public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setHeadless(false);
    app.setBannerMode(Banner.Mode.OFF);
    //app.setLogStartupInfo(false);
		app.run(args);
	}
}
