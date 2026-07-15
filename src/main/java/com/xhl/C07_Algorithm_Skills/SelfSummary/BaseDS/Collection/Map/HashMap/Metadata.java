package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.Map.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-06-13 17:28
 * @Description:
 */
//1. 基础数据载体（POJO）
public class Metadata {
    private String name;
    private long createTime;
    private Map<String, Object> attributes;

    public Metadata(String name) {
        this.name = name;
        this.createTime = System.currentTimeMillis();
        this.attributes = new HashMap<>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // 其他 getter/setter...

}
