package com.gmail.pusichek007;

import java.util.ArrayList;

public class AccountCreationResults {

    private boolean isCreated;
    private String errors;

    public AccountCreationResults(boolean isCreated, String errors){
        this.isCreated = isCreated;
        this.errors = errors;
    }

    public boolean accountIsCreated() { return isCreated; }

    public String getError() {
        return errors;
    }
}
