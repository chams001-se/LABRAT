package com.labrat.itembuilders;

import com.labrat.items.BaseItem;
import com.labrat.items.Item;
import com.labrat.items.ItemType;
import com.labrat.view.ResultText;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * Creates items from default values using the Builder Design Pattern
 */

public class ItemBuilder {
    // Declare variables
    protected String name;
    protected final Set<String> aliases;
    protected final HashMap<ItemType, ResultText> resultTextMap;

    public ItemBuilder() {
        // Default values
        name = "";
        aliases = new HashSet<>();
        resultTextMap = new HashMap<>();
    }

    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder withAliases(String... aliases) {
        for (String alias : aliases) {
            this.aliases.add(alias.toLowerCase());
        }
        return this;
    }

    public ItemBuilder withText(ItemType type, ResultText text) {
        resultTextMap.put(type, text);
        return this;
    }

    public Item build() {
        return new BaseItem(name, aliases, resultTextMap);
    }
}
