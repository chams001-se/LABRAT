package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.items.Readable;

import java.util.List;

public class ReadCommand implements Command {
    private final Actor actor;
    private final List<String> args;
    private final CommandType commandType;

    public ReadCommand(Actor actor, List<String> args) {
        this.actor = actor;
        this.args = args;
        this.commandType = CommandType.READ;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    @Override
    public boolean hasValidArgs() {
        if (args.isEmpty()) return false;

        String target = args.getFirst().toLowerCase();
        return actor.getInventoryItem(target) instanceof Readable;
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public void execute() {
        if (args.isEmpty()) {
            System.out.println("Read what");
            return;
        }

        String target = args.getFirst().toLowerCase();
        var obj = actor.getInventoryItem(target);

        if (!(obj instanceof Readable readable)) {
            System.out.println("You can't read that.");
            return;
        }

        System.out.println(readable.read(actor));
    }
}