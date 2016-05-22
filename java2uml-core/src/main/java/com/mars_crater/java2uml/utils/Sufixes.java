package com.mars_crater.java2uml.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ateixeira on 07-05-2016.
 * <p>
 * TODO Analyse if a regex solution will improve performance.
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

    public static String removeObsoletePrefixAndLastChar(String line, String obsoletePrefix) {
        return line.substring(0, line.length() - 1).replace(obsoletePrefix, "").trim();
    }

    private static String getImportPackage(String importLine) {
        return Sufixes.removeObsoletePrefixAndLastChar(importLine.substring(0, importLine.length() - Sufixes.getImportClassName(importLine).length() - 1), "import");
    }

    public static String[] getImportPackageAndClassName(String importLine) {
        return new String[]{getImportClassName(importLine), getImportPackage(importLine)};
    }

    public static String[] getClassExtendsImports(String lineTrimmed) {

        //Class|Interface|Enum name
        String className = getNameBasedOnRegexPattern(lineTrimmed, "(?<=class |interface |enum ).+?(?= extends| implements| \\{)");
        Pattern patternObjectName;
        Matcher matchesObjectName;

        //Get Object type e.g. Class, Interface, Enum
        patternObjectName = Pattern.compile(".+?(?=class|interface|enum)(.*)( "+className+")");
        matchesObjectName = patternObjectName.matcher(lineTrimmed);

        String objectType = null;
        while (matchesObjectName.find()) {
            objectType = matchesObjectName.group(1);
        }

        //Extension name
        patternObjectName = Pattern.compile("(?<=extends ).+?(?= implements| \\{)");
        matchesObjectName = patternObjectName.matcher(lineTrimmed);
        String extendsClassName = null;
        while (matchesObjectName.find()) {
            extendsClassName = matchesObjectName.group(0);
        }

        //Implementations names
        patternObjectName = Pattern.compile("(?<=implements ).+?(?= \\{)");
        matchesObjectName = patternObjectName.matcher(lineTrimmed);
        String implementsClassName = null;
        while (matchesObjectName.find()) {
            implementsClassName = matchesObjectName.group(0);
        }

        return new String[]{objectType, className, extendsClassName, implementsClassName};
    }

    private static String getNameBasedOnRegexPattern(String lineTrimmed, String regexPattern) {
        Pattern patternObjectName = Pattern.compile(regexPattern);
        Matcher matchesObjectName = patternObjectName.matcher(lineTrimmed);

        String className = null;
        while (matchesObjectName.find()) {
            className = matchesObjectName.group(0);
        }
        return className;
    }
}
