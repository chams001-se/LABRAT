package com.labrat.commands;

import com.labrat.actors.*;

public class MoveCommand implements Command {
    private MainCharacter protagonist;

    public MoveCommand(MainCharacter protagonist) {
        this.protagonist = protagonist;
    }

    @Override
    public void execute() {
        protagonist.moveDirection();
    }
}