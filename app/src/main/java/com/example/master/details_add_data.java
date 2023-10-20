package com.example.master;

public class details_add_data {
    private String Ia_info;
    private String product_name;
    private String mode;
    private String premium;
    private String DOL;
    private String proposal_no;
    private String term;
    private String status;


    public details_add_data(String ia_info, String product_name, String mode, String premium, String DOL, String proposal_no, String term, String status) {
        Ia_info = ia_info;
        this.product_name = product_name;
        this.mode = mode;
        this.premium = premium;
        this.DOL = DOL;
        this.proposal_no = proposal_no;
        this.term = term;
        this.status = status;
    }
    public details_add_data()
    {}
    public String getIa_info() {
        return Ia_info;
    }

    public void setIa_info(String ia_info) {
        Ia_info = ia_info;
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

    public String getDOL() {
        return DOL;
    }

    public void setDOL(String DOL) {
        this.DOL = DOL;
    }

    public String getProposal_no() {
        return proposal_no;
    }

    public void setProposal_no(String proposal_no) {
        this.proposal_no = proposal_no;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
