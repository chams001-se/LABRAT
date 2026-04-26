package com.labrat.items;

import com.labrat.view.ResultText;

public class BasicItemFactory implements ItemFactory {

    @Override
    public Item createItem(
            String name,
            ResultText roomText,
            ResultText examineText,
            boolean readable,
            boolean examinable
    ) {

        if (readable && examinable) {
            return new ReadableExaminableItem(name, roomText, examineText);
        }
        if (readable) {
            return new ReadableItem(name, roomText, examineText);
        }
        if (examinable) {
            return new ExaminableItem(name, roomText, examineText);
        }

        return new Item(name, roomText, examineText);
    }
}