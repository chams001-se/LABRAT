package com.labrat.actorstates;

import com.labrat.actors.Actor;
import com.labrat.commands.CommandType;
import com.labrat.rooms.Direction;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

import javax.xml.transform.Result;

import static com.labrat.commands.CommandType.*;
import static com.labrat.commands.CommandType.HELP;
import static com.labrat.commands.CommandType.HIDE;
import static com.labrat.commands.CommandType.INVENTORY;
import static com.labrat.commands.CommandType.QUIT;
import static com.labrat.commands.CommandType.READ;
import static com.labrat.commands.CommandType.TAKE;

public class HideState extends BaseState {
    public HideState(Actor actor) {
        super(actor);
    }

    @Override
    public CommandType[] getAvailableCommands() {
        return availableCommands;
    }

    protected final CommandType[] availableCommands =
            {
                    USE,
                    READ,
                    HIDE,
                    INVENTORY,
                    HELP,
                    QUIT
            };
    @Override
    public void hide() {
        //TODO implement hide command
        actor.toggleHiding();
        actor.setResultText(new ResultText("You are no longer hiding!", PrinterColor.BLUE));
        actor.changeState(new ExploreState(actor));
    }

    @Override
    public void move(Direction direction) {
        actor.setResultText(new ResultText("Cannot move while hiding!"));
    }
}
