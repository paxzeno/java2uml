package com.mars_crater.java2uml.entities;

import java.util.List;

/**
 * Created by z003jyda on 14-05-2016.
 */
public class ObjectNode implements INodeType {

    private String objectName;

    private String packageName;

    private List<ObjectNode> extendsClass;

    private List<ObjectNode> importClasses;

    private List<ObjectNode> implementsClasses;

    private ObjectTypeEnum objectType;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<ObjectNode> getExtendsClass() {
        return extendsClass;
    }

    public void setExtendsClass(List<ObjectNode> extendsClass) {
        this.extendsClass = extendsClass;
    }

    public List<ObjectNode> getImportClasses() {
        return importClasses;
    }

    public void setImportClasses(List<ObjectNode> importClasses) {
        this.importClasses = importClasses;
    }

    public List<ObjectNode> getImplementsClasses() {
        return implementsClasses;
    }

    public void setImplementsClasses(List<ObjectNode> implementsClasses) {
        this.implementsClasses = implementsClasses;
    }

    public ObjectTypeEnum getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectTypeEnum objectType) {
        this.objectType = objectType;
    }
}
