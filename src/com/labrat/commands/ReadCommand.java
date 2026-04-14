package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.items.Item;
import com.labrat.items.Readable;
import com.labrat.view.ResultText;

public class ReadCommand implements Command {
    private final Actor actor;
    private final String[] args;
    private final CommandType commandType;
    private String target;
    private Item item;

    public ReadCommand(Actor actor, String[] args) {
        this.actor = actor;
        this.args = args;
        this.commandType = CommandType.READ;
    }

    @Override
    public String[] getArgs() {
        return args;
    }

    @Override
    public boolean hasValidArgs() {
        // Valid if there is an item in the current room that can be read
        target = args[0].toLowerCase();
        return actor.getCurrentRoom().getItem(target) instanceof Readable;
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public ResultText getResult() {
        return item.getResultText();
    }

    @Override
    public void execute() {
        item = actor.getCurrentRoom().getItem(target);
    }
}