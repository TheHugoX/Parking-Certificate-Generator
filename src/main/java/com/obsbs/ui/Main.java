package com.obsbs.ui;

import com.obsbs.database.config.DatabaseConfig;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.obsbs.ui.MainPanel;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
  extends Application
{
  public static void main(String[] args)
  {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage)
    throws Exception
  {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);

    Scene scene = new Scene(new MainPanel().getParent());
    primaryStage.setTitle("Parking Certificate Generator");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}