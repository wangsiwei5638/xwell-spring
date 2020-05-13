package com.wsw.common;

import java.util.Properties;

public enum Constant {
    SCAN_PKG("scanPackage","扫描包路径")

    ;

    public String name;
    public String desc;
    Constant(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Object getPropertiesVal(Properties properties,Constant c){
        return properties.get(c);
    }

    public static String getPropertiesStringVal(Properties properties,Constant c){
        Object res;

        if(!((res = properties.get(c.name)) instanceof String)){
            throw new ClassCastException("不能转换为String");
        }
        return (String) res;
    }
}
