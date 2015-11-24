package com.obsbs;

import com.obsbs.ui.panels.MainPanel;
import com.obsbs.ui.panels.YesNoPanel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main
        extends Application {
    @Override
    public void start(Stage primaryStage)
            throws Exception {
        MainPanel mainPanel = new MainPanel();

        Scene scene = new Scene(mainPanel.getNode());

        primaryStage.setTitle("Parking Certificate Generator");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            mainPanel.export();
        });
    }

    public static void openYesNoDialog(String title, String message, final YesNoPanel.YesNoCallback yesNoCallback) {
        if (title == null || message == null || yesNoCallback == null) {
            return;
        }

        YesNoPanel yesNoPanel = new YesNoPanel();
        yesNoPanel.setMessage(message);

        Scene scene = new Scene(yesNoPanel.getNode());

        final Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

        yesNoPanel.setYesNoCallback(new YesNoPanel.YesNoCallback() {
            @Override
            public void onYes() {
                stage.close();
                yesNoCallback.onYes();
            }

            @Override
            public void onNo() {
                stage.close();
                yesNoCallback.onNo();
            }
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}