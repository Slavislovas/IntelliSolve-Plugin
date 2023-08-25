package com.intellisolve.intellisolveplugin.Model;

import com.intellisolve.intellisolveplugin.Enum.Difficulty;

public class Task {
    private String id;
    private String name;
    private String description;
    private String baseCode;
    private String methodCode;
    private Difficulty difficulty;

    private Boolean selected;

    public Task(String id, String name, String description, String baseCode, String methodCode, Difficulty difficulty) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.baseCode = baseCode;
        this.methodCode = methodCode;
        this.difficulty = difficulty;
    }

    public Task(){
        this.id = "default";
        this.name = "default";
        this.description = "default";
        this.baseCode = "default";
        this.methodCode = "default";
        this.difficulty = Difficulty.Easy;
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

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getMethodCode() {
        return methodCode;
    }

    public void setMethodCode(String methodCode) {
        this.methodCode = methodCode;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
