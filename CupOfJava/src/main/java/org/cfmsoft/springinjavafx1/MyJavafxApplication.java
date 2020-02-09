package org.cfmsoft.springinjavafx1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

public class MyJavafxApplication extends Application {

	private ApplicationContext context;

	private Scene scene;

	private String title;

	public void setScene(final Scene scene) {
		this.scene = scene;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@Override
	public void init() {

		final GenericApplicationContext parentContext = new StaticApplicationContext();
		parentContext.getBeanFactory().registerSingleton(this.getClass().getSimpleName(), this);
		parentContext.refresh();

		SpringApplication application = new SpringApplication(MySpringContext.class) {

			@Override
			protected ConfigurableApplicationContext createApplicationContext() {
				// create the Spring context and inject the static parent context
				final ConfigurableApplicationContext applicationContext = super.createApplicationContext();
				applicationContext.setParent(parentContext);
				return applicationContext;
			}

		};

		this.context = application.run();

	}

	@Override
	public void start(final Stage primaryStage) {

		primaryStage.setScene(this.scene);
		primaryStage.setWidth(400);
		primaryStage.setHeight(300);
		primaryStage.setTitle(this.title);
		primaryStage.show();

	}

	@Override
	public void stop() {

		SpringApplication.exit(this.context);

	}

	public static void main(final String[] args) {
		launch(args);
	}

}
