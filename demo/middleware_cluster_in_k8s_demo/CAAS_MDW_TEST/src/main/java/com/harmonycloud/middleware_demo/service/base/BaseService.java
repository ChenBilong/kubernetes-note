package com.harmonycloud.middleware_demo.service.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.UUID;

public class BaseService {
    private static final long serialVersionUID = 6357869213649815390L;

    public String get32UUID(){
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-","");
        return uuid;
    }

    protected JSONObject json = new JSONObject();

    protected JSONArray jsonArray = new JSONArray();
}
