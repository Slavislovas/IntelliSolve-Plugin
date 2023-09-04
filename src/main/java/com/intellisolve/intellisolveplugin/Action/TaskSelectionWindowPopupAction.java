package com.intellisolve.intellisolveplugin.Action;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellisolve.intellisolveplugin.Model.Task;
import com.intellisolve.intellisolveplugin.Util.TaskState;
import com.intellisolve.intellisolveplugin.Wrapper.TaskSelectionDialogWrapper;
import org.jetbrains.annotations.NotNull;

public class TaskSelectionWindowPopupAction extends AnAction{
    private ActionManager actionManager = ActionManager.getInstance();
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try{
            TaskSelectionDialogWrapper taskSelectionDialogWrapper = new TaskSelectionDialogWrapper(true);
            if (taskSelectionDialogWrapper.showAndGet()){
                Task selectedTask = taskSelectionDialogWrapper.getSelectedTask();
                selectedTask.setSelected(true);
                TaskState.getInstance().loadState(selectedTask);
                actionManager.
                        getAction("IntelliSolve.Actions.SolveTaskWindowPopupAction").
                        actionPerformed(e);
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
