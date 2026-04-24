package com.labrat.permissions;

import com.labrat.commands.CommandType;

public interface CommandPermissions {
    boolean isAllowed(CommandType type);
}
