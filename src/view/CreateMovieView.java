package view;

import base.View;
import controller.MainController;
import model.CategoryItem;
import model.MainModel;
import model.data.ActionOnCreateMovie;
import model.data.ActionShowCategories;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class CreateMovieView extends View<MainModel, MainController> {
    private JFrame frame;
    JComboBox c;

    public CreateMovieView(MainModel model, MainController controller, JFrame frame) {
        this.setModel(model);
        this.controller(controller);
        this.frame = frame;
    }

    @Override
    public JComponent render() {
        JPanel viewPanel;
        viewPanel = new JPanel();
        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
        viewPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        viewPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel labelPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("Movie name");
        labelPane.add(label);
        viewPanel.add(labelPane);
        JTextField textField = new JTextField();
        viewPanel.add(textField);
        JPanel labelDescPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelDesc = new JLabel("Movie description");
        labelDescPane.add(labelDesc);
        viewPanel.add(labelDescPane);
        JTextArea textDesc = new JTextArea();
        textDesc.setRows(3);
        textDesc.setLineWrap(true);
        viewPanel.add(textDesc);


        JPanel labelBudgetPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelBudget = new JLabel("Movie budget");
        labelBudgetPane.add(labelBudget);
        viewPanel.add(labelBudgetPane);
        JTextField textBudget = new JTextField();
        PlainDocument docBudget = (PlainDocument) textBudget.getDocument();
        docBudget.setDocumentFilter(new DigitFilter());
        viewPanel.add(textBudget);
        JPanel labelYearPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelYear = new JLabel("Movie year");
        labelYearPane.add(labelYear);
        viewPanel.add(labelYearPane);
        JTextField textYear = new JTextField();
        PlainDocument docYear = (PlainDocument) textYear.getDocument();
        docYear.setDocumentFilter(new DigitFilter());
        viewPanel.add(textYear);
        JPanel labelCategoryPane = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel labelCategory = new JLabel("Select category");
        labelCategoryPane.add(labelCategory);
        viewPanel.add(labelCategoryPane);

        c = new JComboBox();
        c.setRenderer(new CategoryListRenderer());
        viewPanel.add(c);


        JButton createButton = new JButton("Create");


        viewPanel.add(createButton, BorderLayout.SOUTH);
        //viewPanel.setSize(300, 100);
        createButton.addActionListener(e -> {
            if (textField.getText().length() < 4) {
                JOptionPane.showMessageDialog(frame,
                        "Name must be more than 4 symbols",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            } else if (frame != null) {
                frame.dispose();
            }
            // textField.getText()
        });
        controller().requestCategories();
        return viewPanel;
    }

    @Override
    public void onShowCategories(ActionShowCategories data) {
        for (CategoryItem categoryItem : data.getCategories()) {
            c.addItem(categoryItem);
        }


    }
}
