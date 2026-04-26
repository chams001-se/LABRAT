package com.labrat.items;

public class ItemFactoryProvider {

    private static final ItemFactory defaultFactory = new BasicItemFactory();

    public static ItemFactory getFactory() {
        return defaultFactory;
    }
}