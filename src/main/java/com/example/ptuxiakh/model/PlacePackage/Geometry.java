package com.example.ptuxiakh.model.PlacePackage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geometry {
    Location location;
    @JsonProperty("viewport")
    Viewport viewPort;

    public Geometry() {
    }

    public Geometry(Location location, Viewport viewPort) {
        this.location = location;
        this.viewPort = viewPort;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Viewport getViewPort() {
        return viewPort;
    }

    public void setViewPort(Viewport viewPort) {
        this.viewPort = viewPort;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "location=" + location +
                ", viewPort=" + viewPort +
                '}';
    }
}
