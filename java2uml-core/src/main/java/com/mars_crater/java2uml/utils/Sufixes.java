package com.mars_crater.java2uml.utils;

/**
 * Created by ateixeira on 07-05-2016.
 */
public final class Sufixes {

    private static String getLastWord(String phrase) {
        String lastWord = "";
        for (int i = phrase.length() - 1; i >= 0; i--) {
            if (phrase.charAt(i) == '.') {
                break;
            }
            lastWord = phrase.charAt(i) + lastWord;
        }
        return lastWord;
    }

    public static String getFileExtension(String filename) {
        return getLastWord(filename);
    }

    public static String getImportClassName(String importClassName) {
        String className = getLastWord(importClassName);
        //remove ; from the name
        return className.substring(0, className.length() - 1);
    }
}
