package view;

import base.View;
import controller.MainController;
import model.MainModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCategoryView extends View<MainModel, MainController> {
    private JFrame frame;

    public CreateCategoryView(MainModel model, MainController controller, JFrame frame) {
        this.setModel(model);
        this.controller(controller);
        this.frame = frame;
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
        viewPanel.setSize(300, 100);
        createButton.addActionListener(e -> {
            if (textField.getText().length() < 4) {
                JOptionPane.showMessageDialog(frame,
                        "Name must be more than 4 symbols",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                controller().createCategory(textField.getText());
                if (frame != null) {
                    frame.dispose();
                }
            }
            // textField.getText()
        });
        return viewPanel;
    }
}
