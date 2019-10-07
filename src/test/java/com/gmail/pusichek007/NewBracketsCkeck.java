package com.gmail.pusichek007;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Stack;

public class NewBracketsCkeck {

    @DataProvider (name = "stringsForExamination")
        public Object[][] setString(){
            return new Object[][] {
                    {"", true},
                    {"{(({[]}))}", true},
                    {"909((k-io*&}}", false},
                    {"()()()()999", true},
                    {"}{}{}{", false},
                    {")[](", false}};
        }

    @Test (dataProvider = "stringsForExamination")
    public void checkCombinationsOfBrackets(String string, boolean result){
        boolean check = checkBracketsAmountAndOrder(string);
        Assert.assertEquals(check,result);
    }


    public boolean checkBracketsAmountAndOrder(String str){

        char[] charArray = str.toCharArray();
        HashMap<Character, Character> bracketsTypes = new HashMap<Character, Character>();
        bracketsTypes.put(')','(');
        bracketsTypes.put('}','{');
        bracketsTypes.put(']','[');

        Stack<Character> openBrackests = new Stack<Character>();

        for (char i : charArray) {
            if (bracketsTypes.containsValue(i)) {
                openBrackests.push(i);
            } else if (bracketsTypes.containsKey(i)) {
                if (!openBrackests.empty()) {
                    if (openBrackests.peek() == bracketsTypes.get(i)) {
                        openBrackests.pop();
                    }
                } else { return false;}
            }
        }

        System.out.println(openBrackests);
        if (openBrackests.empty()){
            return true;
        } else {return false;}
        }
}