package com.pubnub.braindrain.app.model;

import java.util.ArrayList;
import java.util.List;


public class Layer {

    private String collaboratorID;
    private List<Point> points = new ArrayList<>();

    public String getCollaboratorID() {
        return collaboratorID;
    }

    public void setCollaboratorID(String collaboratorID) {
        this.collaboratorID = collaboratorID;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
