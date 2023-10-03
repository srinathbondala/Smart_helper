package com.example.master;

import java.util.Date;

public class Agent_data {
    String IA_Code,IA_Name;
    String IA_dob,IA_doc;
    String IA_MailId;

    public String getIA_mobile() {
        return IA_mobile;
    }

    public void setIA_mobile(String IA_mobile) {
        this.IA_mobile = IA_mobile;
    }

    String IA_mobile;

    public String getIA_Code() {
        return IA_Code;
    }

    public void setIA_Code(String IA_Code) {
        this.IA_Code = IA_Code;
    }

    public String getIA_Name() {
        return IA_Name;
    }

    public void setIA_Name(String IA_Name) {
        this.IA_Name = IA_Name;
    }

    public String getIA_dob() {
        return IA_dob;
    }

    public Agent_data(String IA_Code, String IA_Name, String IA_dob, String IA_doc, String IA_MailId, String IA_mobile) {
        this.IA_Code = IA_Code;
        this.IA_Name = IA_Name;
        this.IA_dob = IA_dob;
        this.IA_doc = IA_doc;
        this.IA_MailId = IA_MailId;
        this.IA_mobile = IA_mobile;
    }

    public void setIA_dob(String IA_dob) {
        this.IA_dob = IA_dob;
    }

    public String getIA_doc() {
        return IA_doc;
    }

    public void setIA_doc(String IA_doc) {
        this.IA_doc = IA_doc;
    }

    public String getIA_MailId() {
        return IA_MailId;
    }

    public void setIA_MailId(String IA_MailId) {
        this.IA_MailId = IA_MailId;
    }
}
