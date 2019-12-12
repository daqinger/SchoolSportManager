package com.example.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.util.List;

public class SportListClass {
    private int total;
    private List<Sport> rows;

    public SportListClass(int total, List<Sport> rows) {
        this.total = total;
        this.rows = rows;
    }

    public SportListClass() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Sport> getRows() {
        return rows;
    }

    public void setRows(List<Sport> rows) {
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
