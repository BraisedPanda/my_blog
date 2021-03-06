package com.braisedpanda.my.blog.commons.utils;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-31 11:35
 **/
public class FontUtils {

    public static String DBChange(String source){
        String changeStr="";
        changeStr=source.replace("&amp;","&");
        changeStr=changeStr.replace("&nbsp;"," ");
        changeStr=changeStr.replace("&lt;","<");
        changeStr=changeStr.replace("&gt;",">");
        changeStr=changeStr.replace("<br>","\r\n");
        changeStr=changeStr.replace("<font ","[F");
        changeStr=changeStr.replace("<","[");
        changeStr=changeStr.replace(">","]");
        return changeStr;
    }

    public static String HTMLChange(String source){
        String changeStr="";
        changeStr=source.replace("&","&amp;");
        changeStr=changeStr.replace(" ","&nbsp;");
        changeStr=changeStr.replace("<p","<p ");
        changeStr=changeStr.replace("\r\n","<br>");
        changeStr=changeStr.replace("<font","<font ");
        changeStr=changeStr.replace(">",">");
        changeStr=changeStr.replace("</font>","</font>");
        changeStr=changeStr.replace("<a","<a ");
        changeStr=changeStr.replace("</a>","</a>");
        return changeStr;
    }
}

