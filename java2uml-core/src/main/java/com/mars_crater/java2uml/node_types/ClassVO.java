package com.mars_crater.java2uml.node_types;

import java.util.List;

/**
 * Created by ateixeira on 08-05-2016.
 */
public class ClassVO implements INodeType {

    private String classname;

    private String packageName;

    private ClassVO extendsClass;

    private List<ClassVO> importClasses;

    private List<InterfaceVO> implementsClasses;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ClassVO getExtendsClass() {
        return extendsClass;
    }

    public void setExtendsClass(ClassVO extendsClass) {
        this.extendsClass = extendsClass;
    }

    public List<ClassVO> getImportClasses() {
        return importClasses;
    }

    public void setImportClasses(List<ClassVO> importClasses) {
        this.importClasses = importClasses;
    }

    public List<InterfaceVO> getImplementsClasses() {
        return implementsClasses;
    }

    public void setImplementsClasses(List<InterfaceVO> implementsClasses) {
        this.implementsClasses = implementsClasses;
    }
}
