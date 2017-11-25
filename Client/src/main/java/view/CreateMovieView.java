package view;

import base.View;
import controller.MainController;
import model.CategoryItem;
import model.ClientModel;
import model.MainModel;
import model.data.response.ResponseMovieEditedData;
import model.data.response.ResponseShowMovie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class CreateMovieView extends View<ClientModel, MainController> {
    private JFrame frame;
    private JComboBox c;
    private int movieId = Integer.MIN_VALUE;
    private JTextField textField = new JTextField();
    private JTextField textBudget = new JTextField();
    private JTextArea textDesc = new JTextArea();
    private JTextField textYear = new JTextField();
    private int categoryId;

    public CreateMovieView(ClientModel model, MainController controller, JFrame frame) {
        this.setModel(model);
        this.controller(controller);
        this.frame = frame;
    }

    public CreateMovieView(ClientModel model, MainController controller, JFrame frame, int movieId) {
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
            if (frame != null) {
                if (movieId == Integer.MIN_VALUE) {
                    textYear.getText().length();
                    controller().createMovie(textField.getText(),
                            textYear.getText().trim().length() == 0 ? 0
                                    : Integer.parseInt(textYear.getText()),
                            textDesc.getText(),
                            ((CategoryItem) c.getSelectedItem()).getId(),
                            textBudget.getText().trim().length() == 0 ? 0 : Integer.parseInt(textBudget.getText())
                    );
                } else {
                    controller().editMovie(movieId, textField.getText(),
                            textYear.getText().trim().length() == 0 ? 0
                                    : Integer.parseInt(textYear.getText()),
                            textDesc.getText(),
                            ((CategoryItem) c.getSelectedItem()).getId(),
                            textBudget.getText().trim().length() == 0 ? 0 : Integer.parseInt(textBudget.getText())
                    );
                    controller().requestMovie(movieId);

                }
                controller().requestCategory(((CategoryItem) c.getSelectedItem()).getId());
                unsubscribe();
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
    public void onShowMovie(ResponseShowMovie data) {
        textField.setText(data.getMovie().getName());
        textBudget.setText(String.valueOf(data.getMovie().getBudget()));
        textYear.setText(String.valueOf(data.getMovie().getYear()));
        textDesc.setText(data.getMovie().getDescription());
        categoryId = data.getMovie().getGenreId();
        controller().requestCategories();
    }

    @Override
    public void onShowCategoriesForEdit(ResponseMovieEditedData data) {
        if (c.getItemCount() == 0)
            for (CategoryItem categoryItem : data.getCategories()) {
                c.addItem(categoryItem);
                if (categoryId == categoryItem.getId()) {
                    c.setSelectedItem(categoryItem);
                }
            }

    }

}
