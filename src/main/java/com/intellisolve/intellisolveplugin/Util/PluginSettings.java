package com.intellisolve.intellisolveplugin.Util;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellisolve.intellisolveplugin.Model.Task;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "TaskState", storages = {@Storage(value = "IntelliSolve-State.xml")})
public class PluginSettings implements PersistentStateComponent<Task> {

    private Task task;

    @Override
    public @Nullable Task getState() {
        return task;
    }

    @Override
    public void loadState(@NotNull Task state) {
        task = state;
    }

    public static PersistentStateComponent<Task> getInstance(){
        return ServiceManager.getService(PluginSettings.class);
    }
}
