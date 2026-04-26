package com.labrat.items;

import com.labrat.view.ResultText;

public class ReadableExaminableItem extends Item implements Readable, Examinable{
    public ReadableExaminableItem(String name, ResultText roomText, ResultText examineText) {
        super(name, roomText, examineText);
        this.readable = true;
        this.examinable = true;
    }

    @Override
    public ResultText read() {
        return getResultText();
    }

    @Override
    public ResultText examine() {
        return getResultText();
    }
}
