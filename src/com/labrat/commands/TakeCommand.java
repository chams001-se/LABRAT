package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.items.Item;

import java.util.List;

public class TakeCommand implements Command {
    private final Actor actor;
    private final List<String> args;
    private final CommandType commandType;

    public TakeCommand(Actor actor, List<String> args) {
        this.actor = actor;
        this.args = args;
        this.commandType = CommandType.TAKE;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    @Override
    public boolean hasValidArgs() {
        if (args.isEmpty()) return false;

        String target = args.getFirst().toLowerCase();
        return actor.getCurrentRoom().getExaminable(target) instanceof Item;
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public void execute() {
        if (args.isEmpty()) {
            System.out.println("Take what?");
            return;
        }

        String target = args.getFirst().toLowerCase();
        var room = actor.getCurrentRoom();
        var obj = room.getExaminable(target);

        if (!(obj instanceof Item item)) {
            System.out.println("You can't take that.");
            return;
        }

        room.removeItem(target);
        actor.addItem(item);

        System.out.println("You take the " + item.getName() + ".");
    }
}