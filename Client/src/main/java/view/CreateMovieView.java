package main.java.view;

import main.java.base.View;
import main.java.controller.MainController;
import main.java.model.CategoryItem;
import main.java.model.data.response.ResponseStartMovieEdit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class CreateMovieView extends View<MainController> {
    private JFrame frame;
    private JComboBox c;
    private JTextField textField = new JTextField();
    private JTextField textBudget = new JTextField();
    private JTextArea textDesc = new JTextArea();
    private JTextField textYear = new JTextField();
    private MainController controller;
    private ResponseStartMovieEdit data;

    public CreateMovieView(MainController controller, JFrame frame, ResponseStartMovieEdit data) {
        this.controller = controller;
        this.frame = frame;
        this.data = data;
        this.frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (data.getMovie() != null)
                    controller.closeEditMovie(data.getMovie().getId());
            }
        });
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

        viewPanel.add(textField);
        JPanel labelDescPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelDesc = new JLabel("Movie description");
        labelDescPane.add(labelDesc);
        viewPanel.add(labelDescPane);

        textDesc.setRows(3);
        textDesc.setLineWrap(true);
        viewPanel.add(textDesc);


        JPanel labelBudgetPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelBudget = new JLabel("Movie budget");
        labelBudgetPane.add(labelBudget);
        viewPanel.add(labelBudgetPane);

        PlainDocument docBudget = (PlainDocument) textBudget.getDocument();
        docBudget.setDocumentFilter(new DigitFilter());
        viewPanel.add(textBudget);
        JPanel labelYearPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelYear = new JLabel("Movie year");
        labelYearPane.add(labelYear);
        viewPanel.add(labelYearPane);

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


        JButton createButton = new JButton(data.getMovie() == null ? "Create" : "Edit");


        viewPanel.add(createButton, BorderLayout.SOUTH);
        createButton.addActionListener(e -> {
            if (frame != null) {
                if (data.getMovie() == null) {
                    controller.createMovie(textField.getText(),
                            textYear.getText().trim().length() == 0 ? 0
                                    : Integer.parseInt(textYear.getText()),
                            textDesc.getText(),
                            ((CategoryItem) c.getSelectedItem()).getId(),
                            textBudget.getText().trim().length() == 0 ? 0 : Integer.parseInt(textBudget.getText())
                    );
                } else {
                    controller.editMovie(data.getMovie().getId(), textField.getText(),
                            textYear.getText().trim().length() == 0 ? 0
                                    : Integer.parseInt(textYear.getText()),
                            textDesc.getText(),
                            ((CategoryItem) c.getSelectedItem()).getId(),
                            textBudget.getText().trim().length() == 0 ? 0 : Integer.parseInt(textBudget.getText())
                    );

                }
                controller.requestCategory(((CategoryItem) c.getSelectedItem()).getId());
                frame.dispose();
            }

            // textField.getText()
        });
        if (data.getMovie() != null) {
            textField.setText(data.getMovie().getName());
            textBudget.setText(String.valueOf(data.getMovie().getBudget()));
            textYear.setText(String.valueOf(data.getMovie().getYear()));
            textDesc.setText(data.getMovie().getDescription());

        }
        for (CategoryItem categoryItem : data.getCategories()) {
            c.addItem(categoryItem);
            if (data.getMovie() != null) {
                if (data.getMovie().getGenreId() == categoryItem.getId()) {
                    c.setSelectedItem(categoryItem);
                }


            }

        }
        return viewPanel;
    }
}
