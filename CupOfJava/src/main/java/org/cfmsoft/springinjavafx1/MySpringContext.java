package org.cfmsoft.springinjavafx1;

import javax.annotation.PreDestroy;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties
public class MySpringContext {

	@Bean
	public ApplicationRunner applicationRunner(final MyJavafxApplication application) {
		return new ApplicationRunner() {

			@Override
			public void run(final ApplicationArguments args) throws Exception {

				final Parent root = FXMLLoader.load(getClass().getResource("hello.fxml"));
				final Scene scene = new Scene(root);
				application.setScene(scene);

				application.setTitle("My Application");

			}

			@PreDestroy
			public void destroy() {

				// do application cleanup if needed

			}

		};
	}

}
