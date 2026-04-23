package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.audio.SoundEffect;
import com.labrat.items.Item;
import com.labrat.permissions.CheckingInventoryPermissions;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

import java.util.Map;

public class InventoryCommand implements Command{
    private final Actor actor;
    private final String[] args;
    private final CommandType commandType;

    ResultText rt;

    public InventoryCommand(Actor actor, String[] args) {
        this.actor = actor;
        this.args = args;
        this.commandType = CommandType.INVENTORY;
    }

    // Return the arguments in the command
    public String[] getArgs(){
        return args;
    }

    // Performs a check on whether the arguments passed are valid
    public boolean hasValidArgs(){
        return true;
    }

    // Return the CommandType of the Command
    public CommandType getCommandType(){
        return commandType;
    }

    // Return the result of the command as printable text
    public ResultText getResult(){
        return rt;
    }

    // Executing the command calls some form of receiver that contains the functionality that the command delivers.
    public void execute(){
        actor.setPermissions(new CheckingInventoryPermissions());

        StringBuilder inventory = new StringBuilder("INVENTORY\n");
        Map<String, Item> inv = actor.getInventory();

        int i = 0;

        for (String item : inv.keySet()){
            i += 1 % 3;
            inventory.append(actor.getInventoryItem(item).getName()).append("\t");
            if (i == 3) inventory.append("\n");
        }

        rt = new ResultText(inventory.toString(), PrinterColor.GREEN);
    }
}
