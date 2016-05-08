package com.mars_crater.java2uml.lookup;

import com.mars_crater.java2uml.node_types.ClassVO;
import com.mars_crater.java2uml.node_types.INodeType;
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

    private static final String PROJECT_PATH = "C:/Development/Src/FinancialAdvisor";

    private static final String MASK = "java";

    private List<File> javaFiles;

    public LookupJavaHeader() throws Exception {
        final File file = new File(PROJECT_PATH);

        if (file.exists() && file.isDirectory()) {
            this.javaFiles = LookupFiles.getFiles(file, MASK);
        }
    }

    public List<String> getHeader() throws IOException {

        //Map <Package, List<INodeType>> (package name, list of classes under that package)
        final Map<String, List<INodeType>> packageMap = new HashMap<>();
        for (File javaFile : this.javaFiles) {
            final List<INodeType> packages = new ArrayList<>();
            final List<String> classLines = Files.readAllLines(javaFile.toPath());

            for (String classLine : classLines) {
                if (classLine.trim().startsWith("package")) {

                }
            }

            final List<String> packageLine = stream.filter(line -> line.startsWith("package")).collect(Collectors.toList());
            packages.add(packageLine.get(0));
        }

        return packages;
    }

    public List<String> getImports() throws IOException {

        final List<String> imports = new ArrayList<>();

        for (File javaFile : this.javaFiles) {
            final Stream<String> stream = Files.lines(javaFile.toPath());
            imports.addAll(stream.filter(line -> line.startsWith("import")).collect(Collectors.toList()));
        }


        return imports;
    }

    public List<ClassVO> getClassWhatItExtentesAndImplements() {
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
