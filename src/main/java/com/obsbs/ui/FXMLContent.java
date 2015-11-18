package com.obsbs.ui;

import com.obsbs.ui.content.Content;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.io.IOException;

public abstract class FXMLContent<N extends Node> implements Content<N>, Initializable {
    private N node;

    public FXMLContent(String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
            loader.setController(this);
            node = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public N getNode() {
        return node;
    }
}
