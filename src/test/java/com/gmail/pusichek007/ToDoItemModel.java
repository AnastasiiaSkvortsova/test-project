package com.gmail.pusichek007;

public class ToDoItemModel {
    public int id;
    public String name;
    public boolean isComplete;


    public ToDoItemModel(String newName, boolean isCompleteStatus){
        name = newName;
        isComplete = isCompleteStatus;
    }

    public ToDoItemModel(String newName, Boolean isCompleteStatus){
        name = newName;
        isComplete = isCompleteStatus;
    }

    public ToDoItemModel(int existingId, String newName, boolean isCompleteStatus){
        id = existingId;
        name = newName;
        isComplete = isCompleteStatus;
    }

    public ToDoItemModel() {}

}
