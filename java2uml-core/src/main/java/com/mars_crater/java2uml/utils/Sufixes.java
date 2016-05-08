package com.mars_crater.java2uml.utils;

import com.mars_crater.java2uml.node_types.ObjectTypeEnum;

import java.util.ArrayList;
import java.util.List;

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
        return new String[]{getImportPackage(importLine), getImportClassName(importLine)};
    }

    public static String[] getClassExtendsImports(String lineTrimmed) {

        //FIXME Implement a new parsing logic this one is bogus, split line a iterate it differently
        String className = null;
        String extendsClass = null;
        final List<String> interfaceClasses = new ArrayList<>();
        ObjectTypeEnum objectType = null;
        final String[] line = lineTrimmed.split(" ");
        //BOGUS FIXME class extends and implements can be in two different lines.
        for (String word : line) {
            switch (word) {
                case "public":
                case "final":
                case "extends":
                case "implements":
                case ",":
                case "{":
                    break;
                case "class":
                    objectType = ObjectTypeEnum.CLASS;
                    break;
                case "interface":
                    objectType = ObjectTypeEnum.INTERFACE;
                    break;
                case "enum":
                    objectType = ObjectTypeEnum.ENUM;
                    break;
                default:
                    if (className == null) {
                        className = word;
                    } else if (extendsClass == null && !isInterfaceOrEnumClass(objectType)) {
                        extendsClass = word;
                    } else {
                        interfaceClasses.add(word);
                    }
            }
        }

        String interfaceClassesArr = null;
        if (!interfaceClasses.isEmpty()) {
            final StringBuilder interfacesArray = new StringBuilder();
            for (String interfaceClass : interfaceClasses) {
                interfacesArray.append(interfaceClass).append(",");
            }
            interfaceClassesArr = interfacesArray.toString();
        }

        return new String[]{className, extendsClass, interfaceClassesArr};
    }

    private static boolean isInterfaceOrEnumClass(ObjectTypeEnum objectType) {
        return ObjectTypeEnum.INTERFACE.equals(objectType) || ObjectTypeEnum.ENUM.equals(objectType);
    }
}
