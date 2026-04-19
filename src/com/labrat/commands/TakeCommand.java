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

    public String argsToString(){
        StringBuilder itemName = new StringBuilder();
        for (String s : args){
            itemName.append(s).append(" ");
        }
        itemName.deleteCharAt(itemName.length() - 1);

        return itemName.toString().toLowerCase();
    }


    @Override
    public String[] getArgs() {
        return args;
    }

    @Override
    public boolean hasValidArgs() {
        // Valid if there is an item in the current room that can be taken

        String target = argsToString();
        System.out.println(target);
        return actor.getCurrentRoom().hasItem(target);
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
        String itemName = argsToString().toLowerCase();
        System.out.println(itemName);

        var room = actor.getCurrentRoom();
        var item = room.getItem(itemName);

        room.removeItem(itemName);
        actor.addItem(item);

    }
}