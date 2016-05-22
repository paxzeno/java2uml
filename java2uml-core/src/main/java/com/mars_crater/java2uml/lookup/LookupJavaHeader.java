package com.mars_crater.java2uml.lookup;

import com.mars_crater.java2uml.entities.INodeType;
import com.mars_crater.java2uml.entities.ObjectNode;
import com.mars_crater.java2uml.services.ObjectFactory;
import com.mars_crater.java2uml.utils.Sufixes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ateixeira on 07-05-2016.
 */
public class LookupJavaHeader {

    private static final String PROJECT_PATH = "C:\\Users\\z003jyda\\nwr\\FinancialAdvisor";

    private static final String MASK = "java";

    private List<File> javaFiles;

    public LookupJavaHeader() throws Exception {
        final File file = new File(PROJECT_PATH);

        if (file.exists() && file.isDirectory()) {
            this.javaFiles = LookupFiles.getFiles(file, MASK);
        }
    }

    public List<String> getHeader() throws Exception {

        //Map <Package, Map<ClassName, NodeObject>> (package name, list of classes under that package)
        final Map<String, Map<String, INodeType>> packageMap = new HashMap<>();
        for (File javaFile : this.javaFiles) {
            //Map <ImportPackage, List<ImportClassName>>
            final Map<String, List<String>> importsMap = new HashMap<>();
            final List<String> classLines = Files.readAllLines(javaFile.toPath());
            String packageName = null;

            for (String classLine : classLines) {
                final String lineTrimmed = classLine.trim();
                if (packageName == null && lineTrimmed.startsWith("package")) {
                    packageName = Sufixes.removeObsoletePrefixAndLastChar(lineTrimmed, "package");
                }

                if (lineTrimmed.startsWith("import")) {
                    final String[] packageNClassName = Sufixes.getImportPackageAndClassName(lineTrimmed);
                    final String importClassName = packageNClassName[0];
                    final String importPackage = packageNClassName[1];
                    if (importsMap.containsKey(importClassName)) {
                        importsMap.get(importClassName).add(importPackage);
                    } else {
                        final List<String> classImportsName = new ArrayList<>();
                        classImportsName.add(importPackage);
                        importsMap.put(importClassName, classImportsName);
                    }
                }

                //TODO Insert Class, extends and imports here.
                if (lineTrimmed.startsWith("public")) {
                    ObjectFactory.createObjects(packageMap, packageName, importsMap, lineTrimmed);
                    break;
                }

            }

            System.out.println("");
        }
        return null;
    }

    public List<String> getImports() throws IOException {

        final List<String> imports = new ArrayList<>();

        for (File javaFile : this.javaFiles) {
            final Stream<String> stream = Files.lines(javaFile.toPath());
            imports.addAll(stream.filter(line -> line.startsWith("import")).collect(Collectors.toList()));
        }


        return imports;
    }

    public List<ObjectNode> getClassWhatItExtentesAndImplements() {
        return null;
    }

    @Deprecated
    public List<File> getJavaFiles() {
        return javaFiles;
    }

    public static void main(String[] args) throws Exception {
        LookupJavaHeader lookupJavaHeader = new LookupJavaHeader();
        lookupJavaHeader.getHeader().forEach(System.out::println);
        lookupJavaHeader.getImports().forEach(System.out::println);

        System.out.println(Sufixes.getImportClassName(lookupJavaHeader.getImports().get(0)));
    }
}
