package com.labrat.commands;

public interface Command {
    // Executing the command calls some form of receiver that contains the functionality that the command delivers.
    public void execute();
}
