package com.labrat.commandreaders;

import com.labrat.actors.Actor;
import com.labrat.commands.CommandType;
import com.labrat.items.Item;
import com.labrat.items.ItemType;
import com.labrat.view.ResultFormatter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CommandAlias {
    Set<Item> roomItems;
    Set<Item> inventoryItems;

    public CommandAlias() {
        roomItems = new HashSet<>();
        inventoryItems = new HashSet<>();
    }

    // Helper function that converts args String array into a single String
    private String argsToString(String[] words) {
        String[] args;

        if (words.length >= 2) {
            args = new String[words.length-1];
            System.arraycopy(words, 1, args, 0, words.length - 1);
        }
        else {
            args = new String[0];
        }

        StringBuilder itemName = new StringBuilder();
        for (String s : args) {
            itemName.append(s).append(" ");
        }
        itemName.deleteCharAt(itemName.length() - 1);

        return itemName.toString().toLowerCase();
    }

    // Resets the Set so that it can be used for different items
    private void flushItemAlias() {
        roomItems.clear();
        inventoryItems.clear();
    }

    // Adds room items into the Set to check aliasing
    private void addRoomItemAliases(Map<String, Item> items) {
        for (var entry : items.entrySet()) {
            roomItems.add(entry.getValue());
        }
    }

    // Adds inventory items into the Set to check aliasing
    private void addInventoryItemAlias(Map<String, Item> items) {
        for (var entry : items.entrySet()) {
            inventoryItems.add(entry.getValue());
        }
    }

    private String aliasBothItem(String[] words) {
        String itemName = argsToString(words);

        for (Item item : roomItems) {
            if (item.checkAlias(itemName)) {
                return item.getInternalName();
            }
        }
        for (Item item : inventoryItems) {
            if (item.checkAlias(itemName)) {
                return item.getInternalName();
            }
        }

        return words[1];
    }

    // Aliases shorthanded user input to a valid Direction
    // Although we can implement aliases inside of the direction enum, I thought keeping them here would be better since
    // they were an argument and arguments are handled here
    private String aliasDir(String dir) {
        return switch (dir) {
            case "n" ->         "north";
            case "ne" ->        "northeast";
            case "e" ->         "east";
            case "se" ->        "southeast";
            case "s" ->         "south";
            case "sw" ->        "southwest";
            case "w" ->         "west";
            case "nw" ->        "northwest";
            default ->          dir;
        };
    }

    // Aliases "inventory open" and "inventory close"
    private String aliasInvCmd(String[] words) {
        String arg = argsToString(words);

        return switch (arg) {
            case "o" -> "open";
            case "c" -> "close";
            default -> arg;
        };
    }

    public void updateAlias(Actor actor) {
        flushItemAlias();
        addRoomItemAliases(actor.getCurrentRoom().getRoomItems());
        addInventoryItemAlias(actor.getInventory().getItemMap());
    }

    // Does not work properly
    public String[] alias(String[] words) {
        // Check input length
        if (words.length >= 1) {
            CommandType cmd = CommandType.fromString(words[0]);
            if (words.length >= 2) {
                if (cmd == CommandType.MOVE) {
                    words[1] = aliasDir(words[1]);
                }
                if (cmd == CommandType.INVENTORY) { words[1] = aliasInvCmd(words); }
                if (cmd == CommandType.USE ||
                    cmd == CommandType.READ ||
                    cmd == CommandType.EXAMINE ||
                    cmd == CommandType.TAKE ||
                    cmd == CommandType.DROP ||
                    cmd == CommandType.HIDE) {
                    words = new String[]{words[0],aliasBothItem(words)};
                }
            }
        }


        return words;
    }

}
