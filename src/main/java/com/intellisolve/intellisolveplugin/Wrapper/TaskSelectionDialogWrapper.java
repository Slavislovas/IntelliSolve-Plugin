package com.intellisolve.intellisolveplugin.Wrapper;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.SingleSelectionModel;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.util.ui.GridBag;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.intellisolve.intellisolveplugin.Model.Task;
import com.intellisolve.intellisolveplugin.Util.IntelliSolveServerConnection;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskSelectionDialogWrapper extends DialogWrapper {

    private JPanel panel = new JPanel(new GridBagLayout());
    private JTable taskTable = new JBTable();
    private List<Task> taskList = new ArrayList<>();
    private IntelliSolveServerConnection serverConnection = IntelliSolveServerConnection.getInstance();

    public TaskSelectionDialogWrapper(boolean canBeParent) throws IOException {
        super(canBeParent);
        setTitle("IntelliSolve");
        setOKButtonText("Select Task");
        setResizable(false);
        constructTable();
        populateTaskSelectionTable();
        init();
    }

    private void constructTable() {
        taskTable.setSelectionModel(new SingleSelectionModel());
        taskTable.setRowSelectionAllowed(true);
        taskTable.getTableHeader().setReorderingAllowed(false);

        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Id");
        tableModel.addColumn("Name");
        tableModel.addColumn("Difficulty");
        taskTable.setModel(tableModel);

        taskTable.getColumnModel().getColumn(0).setMinWidth(0);
        taskTable.getColumnModel().getColumn(0).setMaxWidth(0);
        taskTable.getColumnModel().getColumn(0).setResizable(false);
    }

    private void populateTaskSelectionTable() throws IOException {
        taskList = serverConnection.getAllTasks();
        DefaultTableModel tableModel = (DefaultTableModel) taskTable.getModel();
        for (Task task : taskList) {
            tableModel.addRow(new String[]{task.getId(), task.getName(), task.getDifficulty().name()});
        }
        taskTable.setModel(tableModel);
    }

    public Task getSelectedTask(){
        int selectedRow = taskTable.getSelectedRow();
        String selectedTaskId = (String) taskTable.getModel().getValueAt(selectedRow, 0);

        return taskList.stream().filter(x -> x.getId().equals(selectedTaskId)).findFirst().get();
    }

    @Override
    protected void doOKAction() {
        if (taskTable.getSelectedRowCount() == 0){
            Messages.showErrorDialog("Please select a task from the list", "Task Not Selected");
        } else{
            super.doOKAction();
        }
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        GridBag gridBag = new GridBag()
                .setDefaultInsets(JBUI.insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL);

        panel.add(label("Task Selection Window", 50, true),
                gridBag.nextLine().next().weightx(0.2));
        panel.add(new JBScrollPane(taskTable), gridBag.nextLine().next().weightx(0.2));
        return panel;
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
