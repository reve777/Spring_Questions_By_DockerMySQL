package com.portfolio.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse { // 商品

    private int statusCode;

    @JsonProperty("Message") // 映射 JSON 中的 "Message" 字段
    private String message;

    @JsonProperty("Data") // 映射 JSON 中的大写 "Data" 字段
    private List<Product> data;

    // Getters and Setters

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
