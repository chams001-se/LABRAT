package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.items.Examinable;

import java.util.List;

public class ExamineCommand implements Command {
    private final Actor actor;
    private final List<String> args;
    private final CommandType commandType;

    public ExamineCommand(Actor actor, List<String> args) {
        this.actor = actor;
        this.args = args;
        this.commandType = CommandType.EXAMINE;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    @Override
    public boolean hasValidArgs() {
        if (args.isEmpty()) { return false; }

        String target = args.getFirst().toLowerCase();
        return actor.getCurrentRoom().getExaminable(target) != null;
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public void execute() {
        if (args.isEmpty()) {
            System.out.println("Examine what");
            return;
        }

        String target = args.getFirst().toLowerCase();
        Examinable obj = actor.getCurrentRoom().getExaminable(target);

        if (obj == null) {
            System.out.println("You don't see that here.");
            return;
        }

        System.out.println(obj.examine(actor));
    }
}