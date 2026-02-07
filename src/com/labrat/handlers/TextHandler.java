package com.labrat.handlers;

public interface TextHandler {
    void performRequest(String[] input);
    boolean canHandleRequest(String[] input);
}
