package com.gmail.pusichek007;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BracketsCheck {


    @Test
        public void checkValidString_Success(){
        String str = "(878*9-3.14)+((886-98))";
        boolean result = checkBracketsValueAndOrder(str);
        Assert.assertEquals(result, true);
    }

    @Test
    public void checkInvalidString_GetError(){
        String str = "(878*9-3.14))(+((886-98))";
        boolean result = checkBracketsValueAndOrder(str);
        Assert.assertEquals(result, false);
    }

    public static boolean checkBracketsValueAndOrder (String str){

        char[] chars = str.toCharArray();
        ArrayList<Character> charList = new ArrayList<Character>();

        for (char i : chars){
            charList.add(i);
        }

        ArrayList<Integer> openBrackets = new ArrayList<Integer>();
        ArrayList<Integer> closeBrackets = new ArrayList<Integer>();


        Integer y = 0;
        for (Character i : charList){
            if (i == '(') {
                openBrackets.add(y);
                System.out.println(y);
            } else if (i == ')') {
                closeBrackets.add(y);
                System.out.println(y
                );
            }
            y++;
        }

        int m;
        boolean result = true;

        if (openBrackets.size() != closeBrackets.size()) {
            result = false;
        } else {
            for (m = 0; m < openBrackets.size() - 1; m++) {
                if (openBrackets.get(m) > closeBrackets.get(m)) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }
}
