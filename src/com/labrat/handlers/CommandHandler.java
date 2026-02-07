package com.labrat.handlers;

import com.labrat.commands.*;

public class CommandHandler {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void processCommand() {
        command.execute();
    }
}
