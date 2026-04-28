package com.labrat.itembuilders;

import com.labrat.items.Item;
import com.labrat.items.LockItem;

public class LockBuilder extends ItemBuilder {
    String keyId;

    public LockBuilder(String keyId) {
        // Default values
        this.keyId = keyId;
    }

    @Override
    public Item build() {
        return new LockItem(super.name, super.aliases, super.resultTextMap, keyId);
    }
}