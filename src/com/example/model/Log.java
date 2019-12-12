package com.example.model;

public class Log {
    private String logContent;//日志主体
    private String logDate;//日志时间

    public Log(String logContent, String logDate) {
        this.logContent = logContent;
        this.logDate = logDate;
    }

    public Log() {
    }

    @Override
    public String toString() {
        return "Log{" +
                "logContent='" + logContent + '\'' +
                ", logDate=" + logDate +
                '}';
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }
}
