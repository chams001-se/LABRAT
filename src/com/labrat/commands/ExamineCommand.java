package com.labrat.commands;

import com.labrat.actors.Actor;

public class ExamineCommand extends BaseCommand {
    public ExamineCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.EXAMINE);
    }

    @Override
    public void execute() {
        actor.examine(argsToString(args));
    }
}