package com.gmail.pusichek007;

public class ExceptionalToDoModel {

    public int id;
    public String name;
    public String isComplete;

    public ExceptionalToDoModel(String itemName, String isCompleteStatus) {
        name = itemName;
        isComplete = isCompleteStatus;
    }
}
