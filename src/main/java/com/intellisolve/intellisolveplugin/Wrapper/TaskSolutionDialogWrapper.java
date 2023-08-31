package com.intellisolve.intellisolveplugin.Wrapper;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabel;
import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.util.ui.GridBag;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.intellisolve.intellisolveplugin.Model.Task;
import com.intellisolve.intellisolveplugin.Util.PluginSettings;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class TaskSolutionDialogWrapper extends DialogWrapper {
    private JPanel panel = new JPanel(new GridBagLayout());
    private JBLabel taskName = new JBLabel();
    private JTextArea taskDescription = new JTextArea();
    private RSyntaxTextArea taskCode = new RSyntaxTextArea();
    private Task task;

    public TaskSolutionDialogWrapper(boolean canBeParent) {
        super(canBeParent);
        setTitle("IntelliSolve");
        setOKButtonText("Submit Solution");
        setResizable(false);
        task = PluginSettings.getInstance().getState();
        if (task != null){
            taskName = label(task.getName(), 50, true, SwingConstants.CENTER);
            taskDescription = textArea(task.getDescription(), 15, false, false);
            taskCode = codeTextArea(task.getMethodCode());
        }
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        GridBag gridBag = new GridBag()
                .setDefaultInsets(JBUI.insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL);

        panel.add(taskName, gridBag.nextLine());

        JPanel panel1 = new JPanel(new GridBagLayout());
        JPanel panel2 = new JPanel(new GridBagLayout());

        panel1.add(taskDescription, gridBag.nextLine());
        panel2.add(new RTextScrollPane(taskCode), gridBag.nextLine());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
        splitPane.setDividerLocation(300);
        splitPane.setDividerSize(0);
        splitPane.setBorder(null);
        panel.add(splitPane, gridBag.nextLine());
        panel.setMinimumSize(new Dimension(800, panel.getSize().height));
        return panel;
    }

    @Override
    public void doCancelAction() {
        task.setName(taskName.getText());
        task.setDescription(taskDescription.getText());
        task.setMethodCode(taskCode.getText());
        super.doCancelAction();
    }

    @Override
    protected void doOKAction() {
        task.setName(taskName.getText());
        task.setDescription(taskDescription.getText());
        task.setMethodCode(taskCode.getText());
        super.doOKAction();
    }

    private JBLabel label(String text, int size, boolean bold, int alignment){
        JBLabel label = new JBLabel(text, alignment);
        label.setComponentStyle(UIUtil.ComponentStyle.LARGE);
        label.setFontColor(UIUtil.FontColor.BRIGHTER);
        label.setFont(new Font(Font.DIALOG, bold ? Font.BOLD : Font.PLAIN, size));
        return label;
    }

    private JTextArea textArea(String text, int size, boolean bold, boolean editable){
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font(Font.DIALOG, bold ? Font.BOLD : Font.PLAIN, size));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(editable);
        return textArea;
    }

    private RSyntaxTextArea codeTextArea(String code) {
        RSyntaxTextArea rSyntaxTextArea = new RSyntaxTextArea(15, 60);
        rSyntaxTextArea.setText(code);
        rSyntaxTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        rSyntaxTextArea.setCodeFoldingEnabled(true);
        rSyntaxTextArea.setAutoIndentEnabled(true);
        rSyntaxTextArea.setAntiAliasingEnabled(true);
        rSyntaxTextArea.setBackground(JBColor.LIGHT_GRAY);
        return rSyntaxTextArea;
    }
}
