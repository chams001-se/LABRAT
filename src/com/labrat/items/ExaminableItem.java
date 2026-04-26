package com.labrat.items;

import com.labrat.view.ResultText;

public class ExaminableItem extends Item implements Examinable{
    public ExaminableItem(String name, ResultText roomText, ResultText examineText) {
        super(name, roomText, examineText);
        this.examinable = true;
    }

    @Override
    public ResultText examine() {
        return getResultText();
    }
}
