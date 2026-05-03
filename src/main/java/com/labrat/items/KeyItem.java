package com.labrat.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.labrat.rooms.Exit;

import java.util.Set;

public class KeyItem extends Item {
    @JsonProperty("ForExits")
    private Set<String> forExits;
    public KeyItem(){
        super();
    }
    public boolean canUnlock(Exit exit) {
        return exit.isLocked() && exit.getKeyRequired().equals(internalName);
    }
}
