package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.items.Examinable;
import com.labrat.view.ResultText;

import java.util.List;

public class ExamineCommand implements Command {
    private final Actor actor;
    private final String[] args;
    private final CommandType commandType;

    public ExamineCommand(Actor actor, String[] args) {
        this.actor = actor;
        this.args = args;
        this.commandType = CommandType.EXAMINE;
    }

    @Override
    public String[] getArgs() {
        return args;
    }

    @Override
    public boolean hasValidArgs() {
        if (args.length == 0) { return false; }

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
        if (args.length == 0) {
            System.out.println("Examine what");
            return;
        }

        String target = args[0].toLowerCase();
        Examinable obj = actor.getCurrentRoom().getItem(target);

        if (obj == null) {
            System.out.println("You don't see that here.");
            return;
        }

        System.out.println(obj.examine());
    }
}