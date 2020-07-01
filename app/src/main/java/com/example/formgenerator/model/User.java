package com.example.formgenerator.model;

public class User {
  private String uid;
  private String name;
  private String lastName;
  private String mail;
  private String pwd;
  private String phone;
  private String Url;
  private int type = 0;



    public User() {
    }

    public User(String uid, String name, String lastName, String mail, String pwd, String phone, String url, int type) {
        this.uid = uid;
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.pwd = pwd;
        this.phone = phone;
        Url = url;
        this.type = type;
    }

    public User( String name, String lastName, String mail, String pwd, String phone, String url, int type) {
        this.uid = uid;
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.pwd = pwd;
        this.phone = phone;
        Url = url;
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
