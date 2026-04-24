package com.labrat.items;

import com.labrat.view.ResultText;

// Examinables are items that place the player into an Examine State, which allows further
// contextual actions via a numerical menu:
// i.e. (1) Pull, (2) Push, (3) Tug

public interface Examinable {
    ResultText examine();
}