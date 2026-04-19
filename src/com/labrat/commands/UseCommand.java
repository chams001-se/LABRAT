package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.audio.SoundEffect;
import com.labrat.items.Item;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

import java.util.Arrays;
import java.util.Map;

public class UseCommand implements Command{
    private final Actor actor;
    private final String[] args;
    private final CommandType commandType;

    ResultText rt;

    public UseCommand(Actor actor, String[] args) {
        this.actor = actor;
        this.args = args;
        this.commandType = CommandType.INVENTORY;
    }

    // Return the arguments in the command
    public String[] getArgs(){
        return args;
    }

    public String argsToString(){
        StringBuilder itemName = new StringBuilder();
        for (String s : args){
            itemName.append(s).append(" ");
        }
        itemName.deleteCharAt(itemName.length() - 1);

        return itemName.toString().toLowerCase();
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
        String itemName = argsToString();
        System.out.println(itemName);
        Item item = actor.getInventoryItem(itemName);
        if (item != null){
            rt = item.getResultText();
        } else {
            rt = new ResultText("Item not in inventory", PrinterColor.RED);
        }
    }
}
