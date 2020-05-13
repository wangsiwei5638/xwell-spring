package com.wsw.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName : XwellBeanDefinitionReader
 * @Description : 用于处理配置文件
 * @Author : wsw
 * @Date: 2020-05-11 13:49
 */
public class XwellBeanDefinitionReader {

    private static String baseScanPath;
    private Properties properties;

    public void init(String[] configs){
        for (String config : configs) {
            //todo


        }
    }

    public void creatBena(){
        if(this.properties == null){
            init();
        }
        baseScanPath = (String) properties.get("baseScanPath");

        this.getClass().getClassLoader();


    }

    public void init(){
        if(properties == null){
            properties = new Properties();
        }
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(is);
            System.out.println("配置文件已加载");
            System.out.println(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                //nothing tod0
            }
        }


    }
}