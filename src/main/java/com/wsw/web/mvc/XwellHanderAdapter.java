package com.wsw.web.mvc;

import com.wsw.annotation.XwellRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : XwellHanderAdapter
 * @Description : 处理器适配器
 * @Author : wsw
 * @Date: 2020-05-18 10:23
 */
public class XwellHanderAdapter {


    /**
     * 执行URL对应controller中的方法
     */
    public XwellModelAndView handle(HttpServletRequest request, HttpServletResponse response, XwellHanderMapping handerMapping)
        throws Exception{

        Map<String, Integer> paramIndexMapping = new HashMap<>();

        Annotation[][] annotations = handerMapping.getMethod().getParameterAnnotations();

        //获取参数位置
        for(int i = 0 ; i < annotations.length ; i ++){
            for (Annotation annotation : annotations[i]) {
                if(annotation instanceof XwellRequestParam){
                    String value = ((XwellRequestParam) annotation).value();
                    if(!"".equals(value.trim())){
                        paramIndexMapping.put(value,i);
                    }
                }
            }
        }

        Class<?>[] parameterTypes = handerMapping.getMethod().getParameterTypes();
        //获取req与resp位置 暂时不允许
//        for(int i = 0 ; i < parameterTypes.length ; i ++){
//            Class<?> clazz = parameterTypes[i];
//            if(clazz == HttpServletRequest.class || clazz == HttpServletResponse.class){
//                paramIndexMapping.put(clazz.getName(),i);
//            }
//        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        //实参列表
        Object[] values = new Object[parameterTypes.length];
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String val = Arrays.toString(parameterMap.get(entry.getKey()))
                    .replaceAll("\\[\\]]", "")
                    .replaceAll("\\s", "");

            Integer index = paramIndexMapping.get(entry.getKey());

            values[index] = val.toString();
        }

        Object res = handerMapping.getMethod().invoke(handerMapping.getController(), values);
        if(res == null || res instanceof Void){
            return null;
        }

        if(handerMapping.getMethod().getReturnType() == XwellModelAndView.class){
            return (XwellModelAndView) res;
        }

        return null;
    }
}