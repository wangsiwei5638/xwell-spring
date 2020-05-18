package com.wsw.common;

import java.util.Properties;

public enum Constant {
    SCAN_PKG("scanPackage","扫描包路径"),
    SCOPE_SINGLETON("singleton","单例"),
    SCOPE_PROTOTYPE("prototype","多例")

    ;

    public String name;
    public String desc;
    Constant(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public static Object getPropertiesVal(Properties properties,Constant c){
        return properties.get(c.name);
    }

    public static String getPropertiesStringVal(Properties properties,Constant c){
        Object res;

        if(!((res = getPropertiesVal(properties,c)) instanceof String)){
            throw new ClassCastException("不能转换为String");
        }
        return (String) res;
    }
}
