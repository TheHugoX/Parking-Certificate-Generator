package com.obsbs.ui.content;

import javafx.scene.Node;

public interface Content<N extends Node> {
    N getNode();
}
