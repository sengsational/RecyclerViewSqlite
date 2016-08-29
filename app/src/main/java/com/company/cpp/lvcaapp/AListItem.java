package com.company.cpp.lvcaapp;

import android.content.ContentValues;

public class AListItem {

    static String rawInputString;

    static String name;
    static String second_line;
    static String hidden;

    static AListItem aListItem;

    private AListItem() {
    }

    public static AListItem getInstance(){
        if (aListItem == null) {
            aListItem = new AListItem();
        }
        return aListItem;
    }

    public static void clear() {
        rawInputString = null;
        name = null;
        second_line = null;
        hidden = null;
    }

    public static ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("SECOND_LINE", second_line);
        values.put("HIDDEN",  hidden);
        return values;
    }


    public static void load(String string) {
        StringBuffer buf = new StringBuffer(string);
        if (buf.substring(0,2).equals("[{")){
            buf.delete(0,2);
        }
        if (buf.substring(buf.length()-2, buf.length()).equals("}]")) {
            buf.delete(buf.length()-2, buf.length());
        }
        rawInputString = buf.toString();
        parse();
    }

    public static void parse() {

        if (rawInputString == null) {
            System.out.println("nothing to parse");
            return;
        }

        rawInputString = rawInputString.replaceAll("\"\\:null,", "\"\\:\"null\",");
        String[] nvpa = rawInputString.split("\",\"");
        for (String nvpString : nvpa) {
            String[] nvpItem = nvpString.split("\":\"");
            if (nvpItem.length < 2) continue;
            String identifier = nvpItem[0].replaceAll("\"", "");
            String content = nvpItem[1].replaceAll("\"", "");

            switch (identifier) {
                case "name":
                    setName(content);
                    break;
                case "second_line":
                    setSecond_line(content);
                    break;
                case "hidden":
                    setHidden(content);
                    break;
                default:
                    System.out.println("nowhere to put [" + nvpItem[0] + "] " + nvpString + " raw: " + rawInputString);
                    break;
            }
        }
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {  AListItem.name = name; }

    public static void setSecond_line(String second_line) {
        AListItem.second_line = second_line;
    }

    public static String getSecond_line() {
        return second_line;
    }

    public static void setHidden(String hidden) {
        AListItem.hidden = hidden;
    }

    public static String getHidden() {
        return hidden;
    }

    public String toString() {
        return getName() + ", " +
                getSecond_line() + ", " +
                getHidden();
    }

}
