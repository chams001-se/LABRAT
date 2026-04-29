package com.labrat.actorstates;

import com.labrat.actors.Actor;
import com.labrat.commands.CommandType;
import com.labrat.items.Item;
import com.labrat.rooms.Direction;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

import static com.labrat.commands.CommandType.*;

// Concrete states override parent methods with error messages for unused commands

public class HideState extends BaseState {
    public HideState(Actor actor) {
        super(actor);
    }

    @Override
    public ActorStateType getActorStateType() {
        return ActorStateType.HIDE;
    }

    protected CommandType[] getAvailableCommands() {
        return availableCommands;
    }
    private final CommandType[] availableCommands = {
        //MOVE,
        //EXAMINE,
        //USE,
        //READ,
        //TAKE,
        //DROP,
        //HIDE,
        UNHIDE,
        //INVENTORY,
        //CLOSEINVENTORY,
        HELP,
        QUIT
    };

    /* Allowed commands */
    @Override
    public void help() { super.help(); } // BaseState prints available commands

    @Override
    public void unhide() {
        actor.setResultText(new ResultText(
                "You step out from hiding.",
                PrinterColor.BLUE
        ));
        actor.changeState(new ExploreState(actor));
    }

    /* Blocked commands */
    @Override
    public void move(Direction direction) {
        actor.setResultText(new ResultText(
                "You cannot move while hiding.\nType \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

    @Override
    public void examine(Item item) {
        actor.setResultText(new ResultText(
                "You cannot examine while hiding.\nType \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

    @Override
    public void use(Item item) {
        actor.setResultText(new ResultText(
                "You cannot use items while hiding.\nType \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

    @Override
    public void drop(Item item) {
        actor.setResultText(new ResultText(
                "You cannot use items while hiding.\nType \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

    @Override
    public void read(Item item) {
        actor.setResultText(new ResultText(
                "You cannot read while hiding.\nType \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

    @Override
    public void take(Item item) {
        actor.setResultText(new ResultText(
                "You cannot take items while hiding.\nType \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

    @Override
    public void inventory(String arg) {
        actor.setResultText(new ResultText(
                "You cannot check your inventory while hiding.\nType \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

    @Override
    public void hide() {
        actor.setResultText(new ResultText(
                "You are already hiding.\nType \"unhide\" to leave hiding.",
                PrinterColor.RED
        ));
    }

}