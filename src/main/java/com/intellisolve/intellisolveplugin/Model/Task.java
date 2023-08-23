package com.intellisolve.intellisolveplugin.Model;

public class Task {
    private String id;
    private String name;
    private String description;
    private String code;
    private Boolean selected = false;

    public Task(String id, String name, String description, String code){
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public Task(){
        this.id = "default";
        this.name = "default";
        this.description = "default";
        this.code = "default";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
