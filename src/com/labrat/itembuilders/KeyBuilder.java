package com.labrat.itembuilders;

import com.labrat.items.Item;
import com.labrat.items.KeyItem;

public class KeyBuilder extends ItemBuilder {
    protected String keyId;
    protected boolean isOneUse;

    public KeyBuilder(String keyId, Boolean isOneUse) {
        // Default values
        this.keyId = keyId;
        this.isOneUse = isOneUse;
    }

    @Override
    public Item build() {
        return new KeyItem(super.name, super.aliases, super.resultTextMap, keyId, isOneUse);
    }
}