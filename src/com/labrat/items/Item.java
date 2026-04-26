package com.labrat.items;

import com.labrat.view.ResultText;

public class Item {
    protected final String name;
    protected final ResultText roomText;
    protected final ResultText examineText;
    protected boolean readable = false;
    protected boolean examinable = false;

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

    public boolean isReadable(){ return readable;}

    public boolean isExaminable(){ return examinable;}
}
