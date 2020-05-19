package com.wsw.web.mvc;

import com.wsw.common.HttpStatusEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : XwellModelAndView
 * @Description : 模型与视图
 * @Author : wsw
 * @Date: 2020-05-18 10:24
 */
public class XwellModelAndView {

    private String viewName;
    private HttpStatusEnum httpStatusEnum;
    private Map<String,?> model;

    public static Map<String,Object> newModelDef(){
        Map<String, Object> res = new HashMap<>();
        return res;
    }

    public XwellModelAndView(String viewName, HttpStatusEnum httpStatusEnum, Map<String, ?> model) {
        this.viewName = viewName;
        this.httpStatusEnum = httpStatusEnum;
        this.model = model;
    }

    public XwellModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
        this.httpStatusEnum = HttpStatusEnum.OK;
    }

    public XwellModelAndView(HttpStatusEnum httpStatusEnum) {
        this.httpStatusEnum = httpStatusEnum;
    }

    public XwellModelAndView() {
        super();
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}