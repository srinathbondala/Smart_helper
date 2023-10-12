package com.example.master;

public class products_data {
    String name,url,code,mode,description;

    public products_data(String name, String url, String code, String mode, String description) {
        this.name = name;
        this.url = url;
        this.code = code;
        this.mode = mode;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public products_data() {
    }

}
