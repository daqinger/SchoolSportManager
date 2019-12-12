package com.example.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
//本类是格式化报名表列表的类，正确的格式才能上传到前端easy ui框架
public class ApplicationListClass {
    private int total;
    private List<Application> rows;

    public ApplicationListClass(int total, List<Application> rows) {
        this.total = total;
        this.rows = rows;
    }

    public ApplicationListClass() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Application> getRows() {
        return rows;
    }

    public void setRows(List<Application> rows) {
        this.rows = rows;
    }
    @Override
    public String toString() {
        //return JSON.toJSONString(this);
        // 含有日期数据的处理。
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
    }
}
