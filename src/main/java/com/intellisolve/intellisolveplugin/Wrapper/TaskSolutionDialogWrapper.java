package com.intellisolve.intellisolveplugin.Wrapper;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBLabel;
import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.util.ui.GridBag;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.intellisolve.intellisolveplugin.Util.PluginSettings;
import com.intellisolve.intellisolveplugin.Model.Task;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class TaskSolutionDialogWrapper extends DialogWrapper {

    private JPanel panel = new JPanel(new GridBagLayout());
    private JBLabel taskName = new JBLabel();
    private JBLabel taskDescription = new JBLabel();
    private JTextArea taskCode = new JTextArea();
    private Task task;

    public TaskSolutionDialogWrapper(boolean canBeParent) {
        super(canBeParent);

        setTitle("IntelliSolve");
        setOKButtonText("Submit Solution");

        task = PluginSettings.getInstance().getState();

        if (task != null){
            taskName = label(task.getName(), 50, true);
            taskDescription = label(task.getDescription(), 30, false);
            taskCode.setText(task.getCode());
        }

        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        GridBag gridBag = new GridBag()
                .setDefaultInsets(JBUI.insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL);

        panel.add(taskName, gridBag.nextLine().next().weightx(0.2));
        panel.add(taskDescription, gridBag.nextLine().next().weightx(0.2));
        panel.add(taskCode, gridBag.nextLine().next().weightx(0.2));

        return panel;
    }

    @Override
    public void doCancelAction() {
        task.setName(taskName.getText());
        task.setDescription(taskDescription.getText());
        task.setCode(taskCode.getText());
        super.doCancelAction();
    }

    private JBLabel label(String text, int size, boolean isBold){
        JBLabel label = new JBLabel(text);
        label.setComponentStyle(UIUtil.ComponentStyle.LARGE);
        label.setFontColor(UIUtil.FontColor.BRIGHTER);

        if (isBold){
            label.setFont(new Font(Font.DIALOG, Font.BOLD, size));
        } else{
            label.setFont(new Font(Font.DIALOG, Font.PLAIN, size));
        }
        return label;
    }
}
