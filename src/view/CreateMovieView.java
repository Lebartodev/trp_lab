package view;

import base.View;
import controller.MainController;
import model.CategoryItem;
import model.MainModel;
import model.data.ActionOnCreateMovie;
import model.data.ActionShowCategories;
import model.data.ActionShowMovie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class CreateMovieView extends View<MainModel, MainController> {
    private JFrame frame;
    JComboBox c;
    private int movieId = Integer.MIN_VALUE;
    JTextField textField = new JTextField();
    JTextField textBudget = new JTextField();
    JTextArea textDesc = new JTextArea();
    JTextField textYear = new JTextField();
    private int categoryId;

    public CreateMovieView(MainModel model, MainController controller, JFrame frame) {
        this.setModel(model);
        this.controller(controller);
        this.frame = frame;
    }

    public CreateMovieView(MainModel model, MainController controller, JFrame frame, int movieId) {
        this.setModel(model);
        this.controller(controller);
        this.frame = frame;
        this.movieId = movieId;
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


        JButton createButton = new JButton(movieId == Integer.MIN_VALUE ? "Create" : "Edit");


        viewPanel.add(createButton, BorderLayout.SOUTH);
        createButton.addActionListener(e -> {
            if (textField.getText().length() < 4) {
                JOptionPane.showMessageDialog(frame,
                        "Name must be more than 4 symbols",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            } else if (frame != null) {
                if (movieId == Integer.MIN_VALUE) {
                    controller().createMovie(textField.getText(),
                            Integer.parseInt(textYear.getText()),
                            textDesc.getText(),
                            ((CategoryItem) c.getSelectedItem()).getId(),
                            Integer.parseInt(textBudget.getText())
                    );
                } else {
                    controller().editMovie(movieId, textField.getText(),
                            Integer.parseInt(textYear.getText()),
                            textDesc.getText(),
                            ((CategoryItem) c.getSelectedItem()).getId(),
                            Integer.parseInt(textBudget.getText())
                    );
                }
                frame.dispose();
            }

            // textField.getText()
        });

        if (movieId != Integer.MIN_VALUE) {
            controller().requestMovie(movieId);
        } else {
            controller().requestCategories();
        }
        return viewPanel;
    }

    @Override
    public void onShowMovie(ActionShowMovie data) {
        textField.setText(data.getMovie().getName());
        textBudget.setText(String.valueOf(data.getMovie().getBudget()));
        textYear.setText(String.valueOf(data.getMovie().getYear()));
        textDesc.setText(data.getMovie().getDescription());
        controller().requestCategories();
    }

    @Override
    public void onShowCategories(ActionShowCategories data) {
        for (CategoryItem categoryItem : data.getCategories()) {
            c.addItem(categoryItem);
            if (categoryId == categoryItem.getId()) {
                c.setSelectedItem(categoryItem);
            }
        }


    }
}
