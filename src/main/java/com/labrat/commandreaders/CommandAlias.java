package com.labrat.commandreaders;

import com.labrat.actors.Actor;
import com.labrat.items.Item;
import com.labrat.items.ItemType;
import com.labrat.view.ResultFormatter;

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
    private String wordsToString(String[] words) {
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

    // Iterate alias through roomItems to find matching item name
    private String aliasRoomItem(String[] words) {
        String itemName = wordsToString(words);

        for (Item item : roomItems) {
            if (item.checkAlias(itemName)) {
                return item.getInternalName();
            }
        }
        return words[1];
    }

    // Iterate alias through inventoryItems to find matching item name
    private String aliasInvItem(String[] words) {
        String itemName = wordsToString(words);

        for (Item item : inventoryItems) {
            if (item.checkAlias(itemName)) {
                return item.getInternalName();
            }
        }
        return words[1];
    }

    private String aliasBothItem(String[] words) {
        String itemName = wordsToString(words);

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

    // Aliases shorthanded user input to a valid CommandType
    private String aliasCmd(String cmdType) {
        return switch (cmdType) {
            case "m" ->         "move";
            case "e" ->         "examine";
            case "t" ->         "take";
            case "d" ->         "drop";
            case "r" ->         "read";
            case "u" ->         "use";
            case "h" ->         "hide";
            case "uh" ->        "unhide";
            case "i" ->         "inventory";
            case "exit" ->      "quit";
            default ->          cmdType;
        };
    }

    // Aliases shorthanded user input to a valid Direction
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
        String arg = wordsToString(words);

        return switch (arg) {
            case "o" -> "open";
            case "c" -> "close";
            default -> arg;
        };
    }

    // Is a command that calls inventory
    private boolean isInvCmd(String cmd) { return cmd.equals("inventory");}

    // Is a command that is utilizes movement
    private boolean isMoveCmd(String cmd) {
        return cmd.equals("move");
    }

    // Is a command that handles items and is room or inventory agnostic
    private boolean isItemCmd(String cmd) {
        return cmd.equals("examine") || cmd.equals("read") || cmd.equals("use");
    }

    // Is a command that handles inventory items
    private boolean isItemInvCmd(String cmd) {
        return cmd.equals("drop");
    }

    // Is a command that handles room items
    private boolean isItemRoomCmd(String cmd) {
        return cmd.equals("take");
    }

    public void updateAlias(Actor actor) {
        flushItemAlias();
        addRoomItemAliases(actor.getCurrentRoom().getRoomItems());
        addInventoryItemAlias(actor.getInventory().getItemMap());
    }

    public String[] alias(String[] words) {
        // Check input length
        if (words.length >= 1) { words[0] = aliasCmd(words[0]); }
        if (words.length >= 2) {
            if (isMoveCmd(words[0])) { words[1] = aliasDir(words[1]); }
            if (isInvCmd(words[0])) { words[1] = aliasInvCmd(words); }
            if (isItemCmd(words[0])) { words[1] = aliasBothItem(words); }
            if (isItemRoomCmd(words[0])) { words[1] = aliasRoomItem(words); }
            if (isItemInvCmd(words[0])) { words[1] = aliasInvItem(words); }
        }

        return words;
    }
}
