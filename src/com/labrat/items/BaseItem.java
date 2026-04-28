package com.labrat.items;

import com.labrat.view.ResultText;

import java.util.HashMap;
import java.util.Set;

public class BaseItem implements Item {
    private final String name;
    private final Set<String> aliases;
    private final HashMap<ItemType, ResultText> resultTextMap;

    public BaseItem(String name, Set<String> aliases, HashMap<ItemType, ResultText> resultTextMap) {
        this.name = name;
        this.aliases = aliases;
        this.resultTextMap = resultTextMap;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean checkAlias(String input) {
        return aliases.contains(input.toLowerCase());
    }

    /*       DEBUG METHOD
    public void printAliases() {
        for (String alias : aliases) {
            System.out.println(alias);
        }
    }
     */

    @Override
    public boolean isItemType(ItemType type) {
        return resultTextMap.containsKey(type);
    }

    @Override
    public ResultText getResultText(ItemType type) {
        return resultTextMap.get(type);
    }

    @Override
    public String toString() {
        return name;
    }
}
