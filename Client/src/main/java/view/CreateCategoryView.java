package main.java.view;

import main.java.base.View;
import main.java.controller.MainController;
import main.java.model.data.response.ResponseException;
import main.java.model.data.response.ResponseStartCategoryEdit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CreateCategoryView extends View<MainController> {
    private JFrame frame;
    private int categoryId = Integer.MIN_VALUE;
    private JTextField textField = new JTextField();

    public CreateCategoryView(MainController controller, JFrame frame) {
        this.controller(controller);
        this.frame = frame;
    }

    public CreateCategoryView(MainController controller, JFrame frame, int categoryId) {
        this.controller(controller);
        this.frame = frame;
        this.categoryId = categoryId;

    }


    @Override
    public JComponent render() {
        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel label = new JLabel("Category name");

        JButton createButton = new JButton(categoryId == Integer.MIN_VALUE ? "Create" : "Edit");
        viewPanel.add(label, BorderLayout.NORTH);
        viewPanel.add(textField, BorderLayout.CENTER);
        viewPanel.add(createButton, BorderLayout.SOUTH);
        viewPanel.setSize(300, 100);
        if (categoryId != Integer.MIN_VALUE) {
            controller().requestCategoryForEdit(categoryId);
        }
        createButton.addActionListener(e -> {
            if (textField.getText().length() < 4) {
                JOptionPane.showMessageDialog(frame,
                        "Name must be more than 4 symbols",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                if (categoryId == Integer.MIN_VALUE)
                    controller().createCategory(textField.getText());
                else {
                    controller().editCategory(categoryId, textField.getText());
                }
                if (frame != null) {
                    frame.dispose();
                }
            }
            // textField.getText()
        });
        return viewPanel;
    }

    @Override
    public void onEditCategory(ResponseStartCategoryEdit data) {
        textField.setText(data.getCategory().getName());
    }

    @Override
    public void onEditCategoryError(ResponseException ex) {
        int input = JOptionPane.showOptionDialog(frame,
                ex.getException().getMessage(),
                "Warning",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
        if (input == JOptionPane.OK_OPTION) {
            if (frame != null) {

            }
        }
    }
}
