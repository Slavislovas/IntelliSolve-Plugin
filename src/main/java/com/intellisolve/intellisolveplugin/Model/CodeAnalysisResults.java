package com.intellisolve.intellisolveplugin.Model;

import com.intellisolve.intellisolveplugin.Wrapper.CodeAnalysisResultsDialogWrapper;

public class CodeAnalysisResults {
    private int totalTestCases;
    private int passedTestCases;
    private Boolean passed;

    public CodeAnalysisResults(int totalTestCases, int passedTestCases, Boolean passed) {
        this.totalTestCases = totalTestCases;
        this.passedTestCases = passedTestCases;
        this.passed = passed;
    }

    public CodeAnalysisResults(){
        this.totalTestCases = 0;
        this.passedTestCases = 0;
        this.passed = false;
    }

    public int getTotalTestCases() {
        return totalTestCases;
    }

    public void setTotalTestCases(int totalTestCases) {
        this.totalTestCases = totalTestCases;
    }

    public int getPassedTestCases() {
        return passedTestCases;
    }

    public void setPassedTestCases(int passedTestCases) {
        this.passedTestCases = passedTestCases;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
}
