package com.labrat.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

// Initializes items for rooms
/* IF ERROR CAUSED BY LOOPING MAIN FUNCTION, IT IS DUE TO A BAD ROOM OR ITEM NAME IN JSON */

public class ItemsCreator {
    // Declare variables
    private final String filename = "/items/ItemProperties.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private ItemsCreatorMap data;

    private static ItemsCreator ic;
    private ItemsCreator() {}

    public static ItemsCreator getInstance() {
        if (ic == null) {
            ic = new ItemsCreator();
        }
        return ic;
    }

    public Map<String, Item> getItemMap() {
        try {
            data = mapper.readValue(getClass().getResourceAsStream(filename), ItemsCreatorMap.class);

            // Assign <ItemType, ResultText> to each item
            for(var entry : data.items.entrySet()) {
                entry.getValue().setInternalName(entry.getKey());
                entry.getValue().setResultTextMap();
            }

            //Item helloworldnote = data.items.get("helloworldnote");
            //System.out.println(helloworldnote.toString());

            return data.items;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Error at ItemsCreator getItemMap()");
    }
}
