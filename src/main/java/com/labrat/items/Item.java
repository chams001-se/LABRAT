package com.labrat.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.labrat.view.ResultText;
import com.labrat.model.ResultTextImport;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


// IMPORTANT, DECIDES WHICH CLASS TO INSTANTIATE AN ITEM DECLARED IN ItemProperties.json
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,  // use a name string as discriminator
        property = "type",            // the JSON field to look at
        defaultImpl = Item.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = KeyItem.class, name = "key"),
})

public class Item {
    private final Map<ItemType, ResultText> resultTextMap;

    // Generic constructor for JSON
    public Item() {
        this.resultTextMap = new HashMap<>();
    }

    protected String internalName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("aliases")
    private HashSet<String> aliases = new HashSet<>();

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

    public boolean checkAlias(String input) {
        return aliases.contains(input);
    }

    public boolean isItemType(ItemType type) {
        return resultTextMap.containsKey(type);
    }

    public ResultText getResultText(ItemType type) {
        return resultTextMap.get(type);
    }

    public boolean isOneUse() {
        return isOneUse;
    }

    // For each pair of item command and result text, creates a resultText object and ItemType enumeration to be mapped to each other
    // Link items at runtime
    public void setResultTextMap() {
        for (var entry : actions.entrySet()) {
            System.out.println(entry.getKey());
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
