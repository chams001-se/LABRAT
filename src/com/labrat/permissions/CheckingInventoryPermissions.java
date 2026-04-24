package com.labrat.permissions;

import com.labrat.commands.CommandType;

public class CheckingInventoryPermissions implements CommandPermissions{
    @Override
    public boolean isAllowed(CommandType type){
        switch (type) {
            case EXAMINE:
                return true;
            case READ:
                return true;
            case USE:
                return true;
            case CLOSEINVENTORY:
                return true;
            case HELP:
                return true;
            case QUIT:
                return true;
            default:
                return false;
        }
    }
}
