package com.example.master;

public class information_add_data {
    String name,code,product_name,mode,premium,date,proposal_no;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProposal_no() {
        return proposal_no;
    }

    public void setProposal_no(String proposal_no) {
        this.proposal_no = proposal_no;
    }

    public information_add_data(String name, String code, String product_name, String mode, String premium, String date, String proposal_no) {
        this.name = name;
        this.code = code;
        this.product_name = product_name;
        this.mode = mode;
        this.premium = premium;
        this.date = date;
        this.proposal_no = proposal_no;
    }
}
