package com.example.formgenerator.model;

public class Form {

    private String id;
    private String form_tittle;
    private String creator;
    private String insert_date;


    public Form(String id, String form_tittle, String creator, String insert_date) {
        this.id = id;
        this.form_tittle = form_tittle;
        this.creator = creator;
        this.insert_date = insert_date;
    }

    public Form( String form_tittle, String creator, String insert_date) {
        this.id = id;
        this.form_tittle = form_tittle;
        this.creator = creator;
        this.insert_date = insert_date;
    }

    public Form() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForm_tittle() {
        return form_tittle;
    }

    public void setForm_tittle(String form_tittle) {
        this.form_tittle = form_tittle;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }
}
