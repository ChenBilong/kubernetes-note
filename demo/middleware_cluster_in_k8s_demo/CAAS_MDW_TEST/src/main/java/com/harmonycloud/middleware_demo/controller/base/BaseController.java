package com.harmonycloud.middleware_demo.controller.base;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class BaseController {
    private static final long SerialVersionUID = 6357869213649815390L;

    /**
     * @param fastJson
     */
    protected JSONObject json = new JSONObject();

    /**
     * fastjson JSONArray
     */
    protected JSONArray jsonArray = new JSONArray();


}
