package com.labrat.commands;
import com.labrat.actors.Actor;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.permissions.HidingPermissions;
import com.labrat.permissions.HidingPermissions;
import com.labrat.view.ResultText;

public class HideCommand implements Command {

    private final Actor actor;
    private final String[] args;
    private boolean success = false;

    public HideCommand(Actor actor, String[] args) {
        this.actor = actor;
        this.args = args;
    }

    @Override
    public String[] getArgs() {
        return args;
    }

    @Override
    public boolean hasValidArgs() {
        // hide never takes arguments
        return args.length == 0;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.HIDE;
    }

    @Override
    public void execute() {
        // TODO: check if currentroom has place to hide

        // switch to hiding permission state
        actor.setPermissions(new HidingPermissions());
        success = true;
    }

    @Override
    public ResultText getResult() {
        if (!success) {
            return new ResultText("There’s nowhere to hide here.");
        }

        return new ResultText("You are successfully hidden.");
    }
}