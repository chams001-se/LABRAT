package com.labrat.view;

/*
This is kind of breaking the MVC principle's separation of concerns. This is because receivers in the command
design pattern that involve returning information to the user, such as Room, must have some knowledge of color,
something the view should only be responsible for. I kept it like this for simplicity, but I had two more ideas to avoid
breaking MVC.

Firstly to simply make the printer have several methods responsible for printing different colors, such as printRed(), printBlue(), etc.
But this seamed tedious and would require some form of interpretation of where the output still came from.

Secondly, we could instead of enumerating colors, enumerate emotions, such as FEAR for orange, DANGER for red, BLUE for sadness or calmness, etc.
This simple renaming would mean the model actually has no idea what these feelings repurpose to in the printer, and could be used somewhere else.
 */
public enum PrinterColor {
    // Standard colors
    RED,
    GREEN,
    YELLOW,
    BLUE,
    MAGENTA,
    CYAN,
    WHITE,

    // Brighter colors
    LIGHT_RED,
    LIGHT_GREEN,
    LIGHT_YELLOW,
    LIGHT_BLUE,
    LIGHT_MAGENTA,
    LIGHT_CYAN,
    LIGHT_WHITE,

    // Darker colors
    DARK_RED,
    DARK_GREEN,
    DARK_YELLOW,
    DARK_BLUE,
    DARK_MAGENTA,
    DARK_CYAN,

    // Effects
    BOLD,
    DIM,
    ITALIC,
    UNDERLINE,
    BLINK,

    DEFAULT;
}