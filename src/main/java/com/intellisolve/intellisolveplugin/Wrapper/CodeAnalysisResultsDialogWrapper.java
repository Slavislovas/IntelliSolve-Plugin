package com.intellisolve.intellisolveplugin.Wrapper;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.util.ui.GridBag;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.intellisolve.intellisolveplugin.Model.CodeAnalysisResults;
import com.intellisolve.intellisolveplugin.Util.IntelliSolveServerConnection;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CodeAnalysisResultsDialogWrapper extends DialogWrapper {
    private CodeAnalysisResults codeAnalysisResults;
    private IntelliSolveServerConnection serverConnection = IntelliSolveServerConnection.getInstance();
    private JPanel panel = new JPanel(new GridBagLayout());
    public CodeAnalysisResultsDialogWrapper(boolean canBeParent, CodeAnalysisResults codeAnalysisResults) throws IOException {
        super(canBeParent);
        this.codeAnalysisResults = codeAnalysisResults;
        setOKButtonText(codeAnalysisResults.getPassed() ? "Complete Task" : "Try Again");
        setCancelButtonText(codeAnalysisResults.getPassed() ? "Back" : "Return To Task Selection");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        GridBag gridBag = new GridBag()
                .setDefaultInsets(JBUI.insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL);

        panel.add(label("Total test cases: " + codeAnalysisResults.getTotalTestCases(), 20, true, SwingConstants.CENTER),
                gridBag.nextLine());
        panel.add(label("Passed test cases: " + codeAnalysisResults.getPassedTestCases(), 20, true, SwingConstants.CENTER),
                gridBag.nextLine());
        panel.add(label("Passed task: " + codeAnalysisResults.getPassed(), 20, true, SwingConstants.CENTER),
                gridBag.nextLine());

        return panel;
    }

    private JBLabel label(String text, int size, boolean bold, int alignment){
        JBLabel label = new JBLabel(text, alignment);
        label.setComponentStyle(UIUtil.ComponentStyle.LARGE);
        label.setFontColor(UIUtil.FontColor.BRIGHTER);
        label.setFont(new Font(Font.DIALOG, bold ? Font.BOLD : Font.PLAIN, size));
        return label;
    }

    public CodeAnalysisResults getCodeAnalysisResults(){
        return this.codeAnalysisResults;
    }
}
