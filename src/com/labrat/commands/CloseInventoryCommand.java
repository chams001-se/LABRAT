package com.labrat.commands;

/*
import com.labrat.actors.Actor;
import com.labrat.permissions.FreeRoamPermissions;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

public class CloseInventoryCommand implements Command {

    private final Actor actor;
    private final String[] args;
    private ResultText rt;

    public CloseInventoryCommand(Actor actor, String[] args) {
        this.actor = actor;
        this.args = args;
    }

    @Override
    public String[] args() {
        return args;
    }

    @Override
    public boolean hasValidArgs() {
        // closeinventory takes no arguments
        return args.length == 0;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CLOSEINVENTORY;
    }

    @Override
    public void execute() {
        actor.setPermissions(new FreeRoamPermissions());

        rt = new ResultText("You close your inventory.", PrinterColor.GREEN);
    }

    @Override
    public ResultText getResult() {
        return rt;
    }
}
 */