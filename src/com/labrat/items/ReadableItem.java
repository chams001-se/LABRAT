package com.labrat.items;

import com.labrat.view.ResultText;

public class ReadableItem extends Item implements Readable{
    public ReadableItem(String name, ResultText roomText, ResultText examineText) {
        super(name, roomText, examineText);
        this.readable = true;
    }

    @Override
    public ResultText read() {
        return getResultText();
    }
}
