package com.intellisolve.intellisolveplugin.Action;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.intellisolve.intellisolveplugin.Model.CodeAnalysisResults;
import com.intellisolve.intellisolveplugin.Model.Task;
import com.intellisolve.intellisolveplugin.Util.IntelliSolveServerConnection;
import com.intellisolve.intellisolveplugin.Util.TaskState;
import com.intellisolve.intellisolveplugin.Wrapper.CodeAnalysisResultsDialogWrapper;
import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;

public class CodeAnalysisResultsWindowPopupAction extends AnAction {
    private ActionManager actionManager = ActionManager.getInstance();
    private IntelliSolveServerConnection serverConnection = IntelliSolveServerConnection.getInstance();
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try{
            Task task = TaskState.getInstance().getState();
            Pair<CodeAnalysisResults, String> requestResults = serverConnection.runTaskSolution(task.getId(), task.getMethodCode());
            if (requestResults.getValue0() == null){
                Messages.showErrorDialog(requestResults.getValue1(), "Error");
                actionManager
                        .getAction("IntelliSolve.Actions.SolveTaskWindowPopupAction")
                        .actionPerformed(e);
            }
            CodeAnalysisResultsDialogWrapper codeAnalysisResultsDialogWrapper = new CodeAnalysisResultsDialogWrapper(true, requestResults.getValue0());
            if (codeAnalysisResultsDialogWrapper.showAndGet()){
                if (codeAnalysisResultsDialogWrapper.getCodeAnalysisResults().getPassed()){
                    task.setSelected(false);
                    TaskState.getInstance().loadState(task);
                    actionManager
                            .getAction("IntelliSolve.Actions.TaskSelectionWindowPopupAction")
                            .actionPerformed(e);
                } else{
                    actionManager
                            .getAction("IntelliSolve.Actions.SolveTaskWindowPopupAction")
                            .actionPerformed(e);
                }
            } else {
                if (codeAnalysisResultsDialogWrapper.getCodeAnalysisResults().getPassed()){
                    actionManager
                            .getAction("IntelliSolve.Actions.SolveTaskWindowPopupAction")
                            .actionPerformed(e);
                } else{
                    actionManager
                            .getAction("IntelliSolve.Actions.TaskSelectionWindowPopupAction")
                            .actionPerformed(e);
                }
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
