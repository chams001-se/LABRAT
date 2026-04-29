package com.labrat.rooms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Exit {
    // Read input from RoomProperties.json
    private String target;
    private boolean locked;
    private String keyRequired;

    public void unlock() {
        locked = false;
        System.out.println(this);
    }

    public String getTarget() {
        return target;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getKeyRequired() {
        return keyRequired;
    }

    @Override
    public String toString() {
        return "Exit{" +
                "target='" + target + '\'' +
                ", locked=" + locked +
                ", keyRequired='" + keyRequired + '\'' +
                '}';
    }
}
