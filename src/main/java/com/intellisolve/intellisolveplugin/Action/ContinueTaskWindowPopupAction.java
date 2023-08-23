package com.intellisolve.intellisolveplugin.Action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.intellisolve.intellisolveplugin.Model.Task;
import com.intellisolve.intellisolveplugin.Util.PluginSettings;
import com.intellisolve.intellisolveplugin.Wrapper.TaskSolutionDialogWrapper;
import org.jetbrains.annotations.NotNull;

public class ContinueTaskWindowPopupAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Task task = PluginSettings.getInstance().getState();
        if (task != null && !task.getSelected()){
            Messages.showErrorDialog("Please select a new task by selecting the button 'Select Task'", "Task not selected");
        } else{
            TaskSolutionDialogWrapper taskSolutionDialogWrapper = new TaskSolutionDialogWrapper(true);
            taskSolutionDialogWrapper.show();
        }
    }
}
