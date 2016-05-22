package com.mars_crater.java2uml.services;

import com.mars_crater.java2uml.entities.*;
import com.mars_crater.java2uml.utils.Sufixes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by z003jyda on 14-05-2016.
 */
public class ObjectFactory {

    public static void createObjects(Map<String, Map<String, INodeType>> packageMap, String packageName, Map<String, List<String>> importsMap, String lineTrimmed) throws Exception {
        final String[] objectNames = Sufixes.getClassExtendsImports(lineTrimmed);

        final ObjectTypeEnum objectType = getObjectType(objectNames[0]);
        final String objectName = objectNames[1];
        final ObjectNode object = new ObjectNode();
        object.setObjectName(objectName);
        object.setPackageName(packageName);
        object.setObjectType(objectType);
        System.out.println(lineTrimmed + "\nobject name: " + object.getObjectName() + " package: " + object.getPackageName());

        final String extendsObjectName = objectNames[2];
        if (extendsObjectName != null) {
            List<String> packageForClassNameExtensionList = importsMap.get(extendsObjectName);
            if (packageForClassNameExtensionList.size() > 1) {
                throw new Exception("Not support extending more than one class with the same name on the import list");
            }

            final ObjectNode extensionObjectName;
            if (!packageForClassNameExtensionList.isEmpty()) {
                extensionObjectName = new ObjectNode();
                extensionObjectName.setObjectName(extendsObjectName);
                extensionObjectName.setPackageName(packageForClassNameExtensionList.get(0));
                final ArrayList<ObjectNode> extensionList = new ArrayList<>();
                extensionList.add(extensionObjectName);
                object.setExtendsClass(extensionList);
            }
        }

        if (packageMap.containsKey(packageName)) {
            Map<String, INodeType> objectNameMap = packageMap.get(packageName);
            if (objectNameMap.containsKey(objectName)) {
                throw new Exception("this object is repeated, and is not supposed to for:" + packageName + objectName);
            } else {
                objectNameMap.put(objectName, object);
            }
        } else {
            final HashMap<String, INodeType> classMap = new HashMap<>();
            classMap.put(object.getObjectName(), object);
            packageMap.put(packageName, classMap);
        }

    }

    private static ObjectTypeEnum getObjectType(String objectType) {
        return ObjectTypeEnum.valueOf(objectType.toUpperCase());
    }
}
