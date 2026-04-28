package com.labrat.items;

import com.labrat.view.ResultText;

import java.util.HashMap;
import java.util.Set;

public class LockItem extends BaseItem {
    private final String keyId;
    private boolean isOpen = false;

    public LockItem(String name, Set<String> aliases, HashMap<ItemType, ResultText> resultTextMap, String keyId) {
        this.keyId = keyId;
        super(name, aliases, resultTextMap);
    }

    public boolean unlock(KeyItem key) {
        if (key.getKeyId().equals(this.keyId)) {
            isOpen = true;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isOpen() {
        return isOpen;
    }
}
