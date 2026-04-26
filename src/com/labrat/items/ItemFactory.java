package com.labrat.items;

import com.labrat.view.ResultText;

public interface ItemFactory {

    Item createItem(
            String name,
            ResultText roomText,
            ResultText examineText,
            boolean readable,
            boolean examinable
    );
}