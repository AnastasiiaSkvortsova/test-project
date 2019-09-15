package com.gmail.pusichek007;

public class SearchCheckBoxes{

   static Argument[] SearchArg = new  Argument[5];

   public static void main (String[] args) {
       SearchArg[0] = new Argument("Argentina");
       SearchArg[1] = new Argument("USA");
       SearchArg[2] = new Argument("China");
       SearchArg[3] = new Argument("Imbir");
       SearchArg[4] = new Argument("Vasilek");

       //for (int i=0; i<5; i++){
       //    System.out.println(SearchArg[i].ArgName);
       //}
       int i=0;
       int k=i+1;
       int m=i+1;

       for (i=0; i<SearchArg.length; i++) {
           SearchArg[i].Selected = true;

           if (SearchArg[m].Selected = false){
           for (k = i+1; SearchArg[k] == SearchArg[SearchArg.length-1]; k++) {
               SearchArg[k].Selected = true;
           }
           }
           if (SearchArg[k] == SearchArg[SearchArg.length-1]) {
               SearchArg[m].Selected = false;
               m = m + 1;
               for (k = SearchArg.length - 1; SearchArg[k] == SearchArg[m]; k--) {
                   SearchArg[k].Selected = false;
               }
               k = k + 1;
           }
       }
   }

}

class Argument {
        String ArgName;
        Boolean Selected;

    public Argument(String ArgName){
        this.ArgName = ArgName;
        }
        }