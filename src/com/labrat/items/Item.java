package com.labrat.items;

import com.labrat.view.ResultText;

public class Item {
    protected final String name;
    protected final ResultText roomText;
    protected final ResultText examineText;

    public Item(String name, ResultText roomText, ResultText examineText) {
        this.name = name;
        this.roomText = roomText;
        this.examineText = examineText;
    }

    public String getName() {
        return name;
    }

    public ResultText getRoomText() { return roomText; }

    public ResultText getResultText() { return examineText; }
}
