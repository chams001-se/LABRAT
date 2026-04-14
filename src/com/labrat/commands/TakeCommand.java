package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.items.Item;
import com.labrat.view.ResultText;

public class TakeCommand implements Command {
    private final Actor actor;
    private final String[] args;
    private final CommandType commandType;

    public TakeCommand(Actor actor, String[] args) {
        this.actor = actor;
        this.args = args;
        this.commandType = CommandType.TAKE;
    }

    @Override
    public String[] getArgs() {
        return args;
    }

    @Override
    public boolean hasValidArgs() {
        // Valid if there is an item in the current room that can be taken
        String target = args[0].toLowerCase();
        return actor.getCurrentRoom().getItem(target) != null;
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
        String target = args[0].toLowerCase();
        var room = actor.getCurrentRoom();
        var obj = room.getItem(target);

        if (!(obj instanceof Item item)) {
            System.out.println("You can't take that.");
            return;
        }

        room.removeItem(target);
        actor.addItem(item);

        System.out.println("You take the " + item.getName() + ".");
    }
}