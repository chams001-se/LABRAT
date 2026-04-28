package com.labrat.items;

import com.labrat.audio.SoundEffect;
import com.labrat.itembuilders.ItemBuilder;
import com.labrat.itembuilders.KeyBuilder;
import com.labrat.itembuilders.LockBuilder;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

import java.util.HashMap;
import java.util.Map;

public class ItemsCreator {
    // Declare variables
    private final Map<Integer, Item> items;

    public ItemsCreator() {
        // Initialize variables
        items = new HashMap<>();

        // Create NullObject item for index 0
        assignIdToItem(0,
                new ItemBuilder()
                .withName("Null Item")
                .build()
        );

        /* Builder pattern applied to create each individual item */
        assignIdToItem(1,
                new ItemBuilder()
                .withName("Hello World")
                .withAliases("note", "hello")
                .withText(ItemType.VISIBLE, new ResultText("There is a note on the ground titled 'Hello World'.", PrinterColor.LIGHT_MAGENTA))
                .withText(ItemType.READABLE, new ResultText("You hold the 'Hello World' note in your hand.\n'The password is FOOBAR.'", PrinterColor.CYAN, SoundEffect.NOTEPAPER))
                .withText(ItemType.TAKABLE, new ResultText("You take the 'Hello World' note in your hand, and put it in your inventory.", PrinterColor.YELLOW, SoundEffect.NOTEPAPER))
                .withText(ItemType.DROPPABLE, new ResultText("You drop the 'Hello World' note onto the ground.", PrinterColor.YELLOW))
                .build()
        );

        assignIdToItem(2,
                new KeyBuilder("test", true)
                .withName("Red Keycard")
                .withAliases("keycard")
                .withText(ItemType.VISIBLE, new ResultText("There is a keycard on the table.", PrinterColor.LIGHT_MAGENTA))
                .withText(ItemType.USABLE, new ResultText("You can't use that here.", PrinterColor.RED, SoundEffect.COMMANDERROR))
                .withText(ItemType.TAKABLE, new ResultText("You take the Red Keycard in your hand, and put it in your inventory.", PrinterColor.YELLOW))
                .withText(ItemType.DROPPABLE, new ResultText("You drop the Red Keycard onto the floor.", PrinterColor.YELLOW))
                .withText(ItemType.KEY, new ResultText("You put the Red Keycard into the reader. It fits perfectly!", PrinterColor.YELLOW, SoundEffect.KEYCARD))
                .build()
        );

        assignIdToItem(3,
                new LockBuilder("test")
                        .withName("Red Keycard Reader")
                        .withAliases("reader")
                        .withText(ItemType.VISIBLE, new ResultText("There is a keycard reader on the north side of the room.", PrinterColor.LIGHT_MAGENTA))
                        .withText(ItemType.USABLE, new ResultText("A keycard needs to be used here.", PrinterColor.RED, SoundEffect.COMMANDERROR))
                        .withText(ItemType.EXAMINABLE, new ResultText("There are red engravings around the reader", PrinterColor.YELLOW))
                        .withText(ItemType.LOCK, new ResultText(""))
                        .build()
        );
    }

    // Helper method to check if duplicate item with index exists
    private void assignIdToItem(int id, Item item) {
        if (!items.containsKey(id)) {
            items.put(id, item);
        }
        else {
            throw new RuntimeException("ItemsCreator: Attempted to override "
                    + items.get(id).toString() + " at " + id +
                    " with " + item.toString());
        }
    }

    public Map<Integer, Item> getItemList() {
        return items;
    }
}
