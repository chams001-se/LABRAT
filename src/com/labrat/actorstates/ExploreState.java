package com.labrat.actorstates;

import com.labrat.actors.Actor;
import com.labrat.commands.CommandType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

import static com.labrat.commands.CommandType.MOVE;
import static com.labrat.commands.CommandType.EXAMINE;
import static com.labrat.commands.CommandType.USE;
import static com.labrat.commands.CommandType.HELP;
import static com.labrat.commands.CommandType.HIDE;
import static com.labrat.commands.CommandType.UNHIDE;
import static com.labrat.commands.CommandType.INVENTORY;
import static com.labrat.commands.CommandType.CLOSEINVENTORY;
import static com.labrat.commands.CommandType.QUIT;
import static com.labrat.commands.CommandType.READ;
import static com.labrat.commands.CommandType.TAKE;

// Concrete states override parent methods with error messages for unused commands

public class ExploreState extends BaseState {
    public ExploreState(Actor actor) {
        super(actor);
    }

    protected final CommandType[] availableCommands =
            {
                    MOVE,
                    EXAMINE,
                    USE,
                    TAKE,
                    READ,
                    HIDE,
                    //UNHIDE,
                    INVENTORY,
                    //CLOSEINVENTORY,
                    HELP,
                    QUIT
            };

    @Override
    public void hide() {
        //TODO implement hide command
        //actor.changeState(new HideState(actor));
    }

    @Override
    public void unhide() {
        actor.setResultText(new ResultText("Cannot unhide because you are currently not hiding.", PrinterColor.RED));
    }
}
