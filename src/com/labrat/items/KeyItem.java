package com.labrat.items;

import com.labrat.view.ResultText;

import java.util.HashMap;
import java.util.Set;

public class KeyItem extends BaseItem {
    private String keyId;
    private boolean isOneUse;

    public KeyItem(String name, Set<String> aliases, HashMap<ItemType, ResultText> resultTextMap, String keyId, Boolean isOneUse) {
        this.isOneUse = isOneUse;
        this.keyId = keyId;
        super(name, aliases, resultTextMap);
    }

    public String getKeyId() {
        return keyId;
    }

    public boolean isOneUse() {
        return isOneUse;
    }
}
