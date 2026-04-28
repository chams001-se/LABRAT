package com.labrat.actorstates;

import com.labrat.actors.Actor;
import com.labrat.commands.CommandType;
import com.labrat.rooms.Direction;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

import static com.labrat.commands.CommandType.*;

public class HideState extends BaseState {
    public HideState(Actor actor) {
        super(actor);
    }

    protected CommandType[] getAvailableCommands() {
        return availableCommands;
    }
    private final CommandType[] availableCommands = {
        //MOVE,
        //EXAMINE,
        //USE,
        //TAKE,
        //READ,
        //HIDE,
        UNHIDE,
        //INVENTORY,
        //CLOSEINVENTORY,
        HELP,
        QUIT
    };

    // Allowed commands
    @Override
    public void unhide() {
        actor.toggleHiding();
        actor.setResultText(new ResultText(
                "You step out from hiding.",
                PrinterColor.BLUE
        ));
        actor.changeState(new ExploreState(actor));
    }

    @Override
    public void help() { super.help(); } // BaseState prints available commands

    // Blocked commands
    @Override
    public void move(Direction direction) {
        actor.setResultText(new ResultText(
                "You cannot move while hiding.\nUse \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

    @Override
    public void examine(String itemName) {
        actor.setResultText(new ResultText(
                "You cannot examine while hiding.\nUse \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

    @Override
    public void use(String itemName) {
        actor.setResultText(new ResultText(
                "You cannot use items while hiding.\nUse \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

    @Override
    public void read(String itemName) {
        actor.setResultText(new ResultText(
                "You cannot read while hiding.\nUse \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

    @Override
    public void take(String itemName) {
        actor.setResultText(new ResultText(
                "You cannot take items while hiding.\nUse \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

    @Override
    public void inventory() {
        actor.setResultText(new ResultText(
                "You cannot check your inventory while hiding.\nUse \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

    @Override
    public void hide() {
        actor.setResultText(new ResultText(
                "You are already hiding.\nUse \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

}