package view;

import base.View;
import controller.MainController;
import model.CategoryItem;
import model.MainModel;
import model.MovieItem;
import model.data.ActionShowCategories;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CategoriesView extends View<MainModel, MainController> {
    private JList<CategoryItem> categoriesList;
    private JList<MovieItem> moviesList;

    public CategoriesView() {
        this.setModel(new MainModel());
        this.controller(new MainController());
    }

    public JComponent render() {
        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        categoriesList = new JList<CategoryItem>();
        categoriesList.setCellRenderer(new CategoryRenderer());
        JScrollPane categoriesPane = new JScrollPane(categoriesList);
        categoriesPane.setBorder(new EmptyBorder(0,10,0,10));
        viewPanel.add(categoriesPane, BorderLayout.WEST);

        moviesList = new JList<MovieItem>();
        JScrollPane moviesPane = new JScrollPane(moviesList);
        viewPanel.add(moviesPane, BorderLayout.CENTER);

        JPanel singleMoviePane = new JPanel(new BorderLayout());
        JButton northButton = new JButton("asdasdasd");
        singleMoviePane.add(northButton, BorderLayout.NORTH);
        viewPanel.add(singleMoviePane, BorderLayout.EAST);


        this.controller().requestCategories();
        return viewPanel;
    }

    @Override
    public void onShowCategories(ActionShowCategories data) {
        categoriesList.setListData(data.getCategories().toArray(new CategoryItem[data.getCategories().size()]));
    }
}
