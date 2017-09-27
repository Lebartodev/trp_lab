package view;

import base.View;
import controller.MainController;
import model.CategoryItem;
import model.MainModel;
import model.MovieItem;
import model.data.ActionShowCategories;
import model.data.ActionShowMoviesInCategory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class CategoriesView extends View<MainModel, MainController> {
    private JList<CategoryItem> categoriesList;
    private JList<MovieItem> moviesList;
    private JPanel viewPanel;
    private JLabel moviesTitle = new JLabel("Select a category");

    public CategoriesView() {
        this.setModel(new MainModel());
        this.controller(new MainController());
    }

    public JComponent render() {

        viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        initCategoriesList();
        initMoviesList();
        initSingleMoviePage();

        this.controller().requestCategories();
        return viewPanel;
    }

    @Override
    public void onShowCategories(ActionShowCategories data) {
        categoriesList.setListData(data.getCategories().toArray(new CategoryItem[data.getCategories().size()]));

    }

    @Override
    public void onShowSingleCategory(ActionShowMoviesInCategory data) {
        moviesList.setListData(data.getMoviesInCategory().getMovies().toArray(new MovieItem[data.getMoviesInCategory().getMovies().size()]));
        moviesTitle.setText(data.getMoviesInCategory().getName());
    }

    private void initCategoriesList() {
        JPanel categoriesPanel = new JPanel(new BorderLayout());
        JLabel categoriesLabel = new JLabel("Categories");
        categoriesLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        categoriesList = new JList<CategoryItem>();
        categoriesList.setCellRenderer(new CategoryRenderer());
        JScrollPane categoriesScroll = new JScrollPane(categoriesList);
        categoriesScroll.setBorder(new EmptyBorder(0, 10, 0, 10));
        categoriesPanel.add(categoriesScroll, BorderLayout.CENTER);
        categoriesPanel.add(categoriesLabel, BorderLayout.NORTH);
        viewPanel.add(categoriesPanel, BorderLayout.WEST);

        categoriesList.addListSelectionListener(e -> controller().requestCategory(categoriesList.getSelectedValue().getId()));
    }

    private void initMoviesList() {
        JPanel moviesPanel = new JPanel(new BorderLayout());
        moviesTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
        moviesList = new JList<MovieItem>();
        moviesList.setCellRenderer(new MovieRenderer());
        JScrollPane moviesScroll = new JScrollPane(moviesList);
        moviesPanel.add(moviesScroll, BorderLayout.CENTER);
        moviesPanel.add(moviesTitle, BorderLayout.NORTH);

        viewPanel.add(moviesPanel, BorderLayout.CENTER);
    }

    private void initSingleMoviePage() {
        JPanel moviePanel = new JPanel(new BorderLayout());

        moviePanel.add(new JLabel("Placeholder"), BorderLayout.CENTER);

        viewPanel.add(moviePanel, BorderLayout.EAST);

    }

}
