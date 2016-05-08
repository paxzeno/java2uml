package com.mars_crater.java2uml.lookup;

import com.mars_crater.java2uml.utils.Sufixes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ateixeira on 07-05-2016.
 */
public class LookupFiles {


    public static List<File> getFiles(File rootFile, String mask) {

        List<File> listOfMaskFiles = new ArrayList<>();

        for (File file : rootFile.listFiles()) {
            if (file.isDirectory()) {
                listOfMaskFiles.addAll(LookupFiles.getFiles(file, mask));
            } else {
                final String filename = file.getName();

                String extension = Sufixes.getFileExtension(filename);

                if (!extension.equals(mask)) {
                    continue;
                }

                listOfMaskFiles.add(file);
            }
        }

        return listOfMaskFiles;
    }

}
