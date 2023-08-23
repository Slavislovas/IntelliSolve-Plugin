package com.intellisolve.intellisolveplugin.Action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellisolve.intellisolveplugin.Model.Task;
import com.intellisolve.intellisolveplugin.Util.PluginSettings;
import com.intellisolve.intellisolveplugin.Wrapper.TaskSelectionDialogWrapper;
import com.intellisolve.intellisolveplugin.Wrapper.TaskSolutionDialogWrapper;
import org.jetbrains.annotations.NotNull;

public class TaskSelectionWindowPopupAction extends AnAction{

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        TaskSelectionDialogWrapper taskSelectionDialogWrapper = new TaskSelectionDialogWrapper(true);

        if (taskSelectionDialogWrapper.showAndGet()){
            Task selectedTask = taskSelectionDialogWrapper.getSelectedTask();
            selectedTask.setSelected(true);
            PluginSettings.getInstance().loadState(selectedTask);
            TaskSolutionDialogWrapper taskSolutionDialogWrapper = new TaskSolutionDialogWrapper(true);
            taskSolutionDialogWrapper.show();
        }
    }
}
