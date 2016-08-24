package com.pubnub.braindrain.app.model;


import java.util.ArrayList;
import java.util.List;


public class Board {

    private String boardName;
    //Phase One
    private List<Layer> layers = new ArrayList<Layer>();

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(ArrayList<Layer> layers) {
        this.layers = layers;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
}
