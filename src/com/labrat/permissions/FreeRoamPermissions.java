package com.labrat.permissions;

import com.labrat.commands.CommandType;

public class FreeRoamPermissions implements CommandPermissions{
    @Override
    public boolean isAllowed(CommandType type){
        return true; // all commands are allowed
    }
}
