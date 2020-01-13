package com.example.salamatapp.CommonLayer;

public class PersianCalender {
    
    public String PersianToEnglish(String persianStr)
    {
        String newnumber="";
        for (char ch: persianStr.toCharArray()) {
            if (ch=='۰'){
                newnumber+="0";
            }else if (ch=='۱'){
                newnumber+="1";
            }else if (ch=='۲'){
                newnumber+="2";
            }else if (ch=='۳'){
                newnumber+="3";
            }else if (ch=='۴'){
                newnumber+="4";
            }else if (ch=='۵'){
                newnumber+="5";
            }else if (ch=='۶'){
                newnumber+="6";
            }else if (ch=='۷'){
                newnumber+="7";
            }else if (ch=='۸'){
                newnumber+="8";
            }else if (ch=='۹'){
                newnumber+="9";
            }else {
                newnumber+=ch;
            }
        }
        return newnumber;
    }
}

