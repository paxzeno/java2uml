package com.mars_crater.java2uml.node_types;

import java.util.List;

/**
 * Created by ateixeira on 08-05-2016.
 */
public class ClassVO implements INodeType {

    private String classname;

    private ClassVO extendsClassName;

    private List<InterfaceVO> implementsClassNames;
}
