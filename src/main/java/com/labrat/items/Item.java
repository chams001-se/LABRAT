package com.labrat.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.labrat.view.ResultText;
import com.labrat.model.ResultTextImport;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Item {
    private final Map<ItemType, ResultText> resultTextMap;

    // Generic constructor for JSON
    public Item() {
        this.resultTextMap = new HashMap<>();
    }

    private String internalName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("aliases")
    private HashSet<String> aliases;

    @JsonProperty("actions")
    private Map<String, ResultTextImport> actions;

    @JsonProperty("isOneUse")
    private boolean isOneUse;
    // Disappears from the game once the player uses a Use command on it

    // Getters
    public String getInternalName() {
        return internalName;
    }

    public String getName() {
        return name;
    }

    //@Override
    public boolean checkAlias(String input) {
        return aliases.contains(input);
    }

    //@Override
    public boolean isItemType(ItemType type) {
        return resultTextMap.containsKey(type);
    }

    //@Override
    public ResultText getResultText(ItemType type) {
        return resultTextMap.get(type);
    }

    //@Override
    public ResultText use() {
        return getResultText(ItemType.USABLE);
    }

    //@Override
    public boolean isOneUse() {
        return isOneUse;
    }

    // Link items at runtime
    //@Override
    public void setResultTextMap() {
        for (var entry : actions.entrySet()) {
            ItemType itemType = ItemType.fromString(entry.getKey());
            ResultText resultText = entry.getValue().newResultText();
            resultTextMap.put(itemType, resultText);
        }
    }

    public Map<ItemType, ResultText> getResultTextMap() {
        return resultTextMap;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    @Override
    public String toString() {
        return name;
    }
}
