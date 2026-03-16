package com.labrat.commandhandlers;

public interface CommandHandler {
    String performRequest(String[] input);
    boolean canHandleRequest(String[] input);
}
