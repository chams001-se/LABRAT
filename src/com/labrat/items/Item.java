package com.labrat.items;

import com.labrat.view.ResultText;

public interface Item {
    // Returns the name of the item
    String getName();

    // Checks if the input matches one of the aliases
    boolean checkAlias(String input);

    // Checks if the type matches one of the possible item types
    boolean isItemType(ItemType type);

    // Returns the ResultText corresponding to the ItemType
    ResultText getResultText(ItemType type);
}
