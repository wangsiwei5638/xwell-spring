package com.wsw.support;

import com.wsw.common.Constant;
import com.wsw.common.Func;
import com.wsw.context.XwellBeanDefinition;
import com.wsw.exception.IOCException;
import com.wsw.home.Test1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @ClassName : XwellBeanDefinitionReader
 * @Description : 用于处理配置文件
 * @Author : wsw
 * @Date: 2020-05-11 13:49
 */
public class XwellBeanDefinitionReader {

    private static String baseScanPath;
    private static String basePkg;
    private Properties properties;
    private Set<String> regiestBeanClasses = new HashSet();

    public XwellBeanDefinitionReader(String config) throws IOCException {
        initCfg(config);
        doScanner();
    }

    //    public void creatBena(){
//        if(this.properties == null){
//            init();
//        }
//        baseScanPath = (String) properties.get("baseScanPath");
//
//        this.getClass().getClassLoader();
//
//
//    }

    /**
     * 生成beanDefinition对象
     */
    public List<XwellBeanDefinition> loadBeanDefinition() throws ClassNotFoundException {
        List<XwellBeanDefinition> beanDefinitions = new LinkedList<XwellBeanDefinition>();
        f1:for (String className : regiestBeanClasses) {
            System.out.println(className);
            Class<?> clazz = Class.forName(className);
            if(clazz.isInterface()){
                continue f1;
            }
            //封装
            XwellBeanDefinition beanDefinition = new XwellBeanDefinition();
            String simpleName = clazz.getSimpleName();
            beanDefinition.setBeanClassName(clazz.getName());
            beanDefinition.setFactoryBeanName(Func.toLowerFristCase(simpleName));
            beanDefinitions.add(beanDefinition);
        }
        return beanDefinitions;
    }

    /**
     * 扫描配置文件下的包
     */
    private void doScanner() throws IOCException {
        if(properties == null){
            throw new IOCException("配置文件为空");
        }
        //
        basePkg = Constant.getPropertiesStringVal(properties, Constant.SCAN_PKG);
        System.out.println("开始扫描"+basePkg+"包下class文件");
        pcs(null);
        System.out.println("扫描完成");
    }

    private void pcs(File root) {
        if(root == null){
            URL url = this.getClass().getClassLoader().getResource("");
            root = new File(url.getFile() + basePkg.replaceAll("\\.","/"));
        }
        f1:for (File file : root.listFiles()) {
            if(file.isDirectory()){
                pcs(file);
            }else{
                if( !file.getName().endsWith(".class")){
                    continue f1;
                }
                String className = basePkg.concat(
                        file.getPath().replaceAll(".*"+basePkg,"")
                        .replace(File.separator,".")
                        .replace(".class","")
                );
                regiestBeanClasses.add(className);
            }
        }
    }

    /**
     * 初始化配置文件，获取配置相关信息
     */
    private void initCfg(String config){
        if(properties == null){
            properties = new Properties();
        }
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(config);
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
                //nothing todo
            }
        }
    }
}