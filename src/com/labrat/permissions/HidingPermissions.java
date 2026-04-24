package com.labrat.permissions;

import com.labrat.commands.CommandType;

public class HidingPermissions implements CommandPermissions {
    @Override
    public boolean isAllowed(CommandType type) {
        switch (type) {
            case EXAMINE:
                return true;
            case UNHIDE:
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
