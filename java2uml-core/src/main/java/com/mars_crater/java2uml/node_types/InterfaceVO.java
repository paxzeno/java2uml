package com.mars_crater.java2uml.node_types;

import java.util.List;

/**
 * Created by ateixeira on 08-05-2016.
 */
public class InterfaceVO implements INodeType {

    private String interfaceName;

    private String packageName;

    private List<InterfaceVO> extendInterfaces;
}
