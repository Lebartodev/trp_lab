package view;

import base.View;
import controller.MainController;
import model.data.response.ResponseException;
import model.data.response.ResponseStartCategoryEdit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CreateCategoryView extends View<MainController> {
    private JFrame frame;
    private int categoryId = Integer.MIN_VALUE;
    private JTextField textField = new JTextField();
    private MainController controller;
    private ResponseStartCategoryEdit data;

    public CreateCategoryView(MainController controller, JFrame frame) {
        this.controller = controller;
        this.frame = frame;
    }

    public CreateCategoryView(MainController controller, JFrame frame, ResponseStartCategoryEdit data) {
        this.data = data;
        this.controller = controller;
        this.frame = frame;

        this.frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                controller.closeEditCategory(data.getCategory().getId());
            }
        });

    }


    @Override
    public JComponent render() {
        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel label = new JLabel("Category name");

        JButton createButton = new JButton(data == null ? "Create" : "Edit");
        viewPanel.add(label, BorderLayout.NORTH);
        viewPanel.add(textField, BorderLayout.CENTER);
        viewPanel.add(createButton, BorderLayout.SOUTH);
        viewPanel.setSize(300, 100);
        createButton.addActionListener(e -> {
            if (textField.getText().length() < 4) {
                JOptionPane.showMessageDialog(frame,
                        "Name must be more than 4 symbols",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                if (data == null)
                    controller.createCategory(textField.getText());
                else {
                    controller.editCategory(data.getCategory().getId(), textField.getText());
                }
                if (frame != null) {
                    frame.dispose();
                }
            }
            // textField.getText()
        });

        if (data != null) {
            textField.setText(data.getCategory().getName());
        }
        return viewPanel;
    }
}
