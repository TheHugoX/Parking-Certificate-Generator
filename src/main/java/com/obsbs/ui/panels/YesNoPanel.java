package com.obsbs.ui.panels;

import com.obsbs.ui.FXMLContent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.*;

public class YesNoPanel extends FXMLContent<BorderPane> {
    @FXML
    private Label message;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    public YesNoPanel() {
        super("yes-no-panel.fxml");
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    public boolean setMessage(String message) {
        if (message == null) {
            return false;
        }
        this.message.setText(message);
        return true;
    }

    public boolean setYesNoCallback(final YesNoCallback yesNoCallback) {
        if (yesNoCallback == null) {
            return false;
        }
        yesButton.setOnMouseClicked(event -> yesNoCallback.onYes());
        noButton.setOnMouseClicked(event -> yesNoCallback.onNo());
        return true;
    }

    public interface YesNoCallback {
        void onYes();

        void onNo();
    }

    public static abstract class AbstractYesNoCallback implements YesNoCallback {
        @Override
        public void onNo() {

        }
    }
}
