package com.labrat.actorstates;

import com.labrat.actors.Actor;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.CommandType;
import com.labrat.items.Item;
import com.labrat.items.ItemType;
import com.labrat.rooms.Direction;
import com.labrat.rooms.Room;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

import java.util.Map;

// BaseState implements default behaviors for all commands
// that are not dependent on state transitions
// i.e. ExploreState -> HideState or HideState -> ExploreState

public abstract class BaseState implements ActorState {
    protected Actor actor;

    public BaseState(Actor actor) {
        this.actor = actor;
    }

    // Return the type of ActorState to avoid instanceof checks
    public abstract ActorStateType getActorStateType();

    // Since BaseState serves as an abstract class, there are no available commands in this state
    // This method will be implemented inside of concrete states where their available commands will simply be returned
    protected abstract CommandType[] getAvailableCommands();

    // Commands are implemented by concrete states
    @Override
    public void help() {
        StringBuilder cmdlist = new StringBuilder("VALID COMMANDS\n");
        for (CommandType c : getAvailableCommands()) {
            cmdlist.append(c.getCommandNotation()).append(": ").append(c.getCommandDescription()).append("\n");
        }

        actor.setResultText(new ResultText(cmdlist.toString(), PrinterColor.DIM, SoundEffect.BEEP));
    }
}
