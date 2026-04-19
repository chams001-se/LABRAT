package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.view.ResultText;

public class HideCommand implements Command {
    private final Actor actor;
    private final CommandType commandType;

    public HideCommand(Actor actor) {
        this.actor = actor;
        this.commandType = CommandType.HIDE;
    }

    @Override
    public String[] getArgs() {
        // HideCommand takes no args
        return null;
    }

    @Override
    public boolean hasValidArgs() {
        // TODO: Check if currentRoom has place to hide
        return true;
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public ResultText getResult() {
        return null;
    }

    @Override
    public void execute() {
        // TODO Toggle between true and false instead of set boolean
        actor.setSneaking(true);
    }
}