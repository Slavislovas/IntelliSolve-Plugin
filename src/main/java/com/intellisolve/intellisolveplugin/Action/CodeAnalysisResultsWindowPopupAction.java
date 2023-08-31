package com.intellisolve.intellisolveplugin.Action;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellisolve.intellisolveplugin.Model.Task;
import com.intellisolve.intellisolveplugin.Util.PluginSettings;
import com.intellisolve.intellisolveplugin.Wrapper.CodeAnalysisResultsDialogWrapper;
import org.jetbrains.annotations.NotNull;

public class CodeAnalysisResultsWindowPopupAction extends AnAction {
    private ActionManager actionManager = ActionManager.getInstance();
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try{
            Task task = PluginSettings.getInstance().getState();
            CodeAnalysisResultsDialogWrapper codeAnalysisResultsDialogWrapper =
                    new CodeAnalysisResultsDialogWrapper(true, task.getId(), task.getMethodCode());
            if (codeAnalysisResultsDialogWrapper.showAndGet()){
                if (codeAnalysisResultsDialogWrapper.getCodeAnalysisResults().getPassed()){
                    actionManager.getAction("IntelliSolve.Actions.TaskSelectionWindowPopupAction").actionPerformed(e);
                } else{
                    actionManager.getAction("IntelliSolve.Actions.SolveTaskWindowPopupAction").actionPerformed(e);
                }
            } else {
                actionManager.getAction("IntelliSolve.Actions.SolveTaskWindowPopupAction").actionPerformed(e);
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
