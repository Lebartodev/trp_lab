package view;

import base.View;
import controller.MainController;
import model.MainModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CreateCategoryView extends View<MainModel, MainController> {

    public CreateCategoryView(MainModel model, MainController controller) {
        this.setModel(model);
        this.controller(controller);
    }

    @Override
    public JComponent render() {
        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel label = new JLabel("Category name");
        JTextField textField = new JTextField();
        JButton createButton = new JButton("Create");
        viewPanel.add(label, BorderLayout.NORTH);
        viewPanel.add(textField, BorderLayout.CENTER);
        viewPanel.add(createButton, BorderLayout.SOUTH);
        viewPanel.setSize(300,100);
        return viewPanel;
    }
}
