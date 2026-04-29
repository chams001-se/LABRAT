package com.labrat.actorstates;

import com.labrat.items.Item;
import com.labrat.rooms.Direction;

// Permissions are depreciated for ActorState

public interface ActorState {
    ActorStateType getActorStateType();
    void help();
    void move(Direction dir);
    void use(Item item);
    void read(Item item);
    void take(Item item);
    void drop(Item item);
    void examine(Item item);
    void inventory(String arg);
    void hide();
    void unhide();
}
