package com.labrat.items;

import com.labrat.rooms.Exit;

public class KeyItem extends Item {
    public KeyItem(){
        super();
    }
    public boolean canUnlock(Exit exit) {
        return exit.isLocked() && exit.needsKey(internalName);
    }
}
