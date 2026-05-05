package com.labrat.rooms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Exit {
    // Read input from RoomProperties.json
    @JsonProperty("target")
    private String target;

    @JsonProperty("locked")
    private boolean locked;

    @JsonProperty("keyRequired")
    private List<String> keyRequired;

    @JsonProperty("numLocks")
    private int numLocks;

    private int currentLocks = 0;

    public void unlock() {
        // Increment current amount of locks opened
        currentLocks++;
        System.out.println("currentLocks " + currentLocks);
        System.out.println("numLocks " + numLocks);

        // Check if number of keys used exceeds number of locks
        if (currentLocks >= numLocks) {
            locked = false;
        }
    }

    public String getTarget() {
        return target;
    }

    public boolean isLocked() {
        return locked;
    }

    public List<String> getKeyRequired() {
        return keyRequired;
    }

    public boolean needsKey(String str) {
        return keyRequired.contains(str);
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
