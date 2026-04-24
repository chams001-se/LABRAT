package com.labrat.actorstates;

import com.labrat.rooms.Direction;

// Permissions are depreciated for ActorState

public interface ActorState {
    void help();
    void move(Direction dir);
    void use(String itemName);
    void read(String itemName);
    void take(String itemName);
    void examine(String itemName);
    void inventory();
}
