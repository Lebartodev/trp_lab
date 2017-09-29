package view;

import base.View;
import controller.MainController;
import model.CategoryItem;
import model.MainModel;
import model.MovieItem;
import model.data.ActionShowCategories;
import model.data.ActionShowMovie;
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
    private JLabel singleMovieTitle = new JLabel("Select movie");
    private JTextArea movieDescription = new JTextArea("asd");
    private JPanel moviesPanel;
    private JPanel moviePanel;


    public CategoriesView(MainModel model, MainController controller) {
        this.setModel(model);
        this.controller(controller);
    }

    public JComponent render() {

        viewPanel = new JPanel();
        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.X_AXIS));
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
        moviesList.setListData(data.getMoviesInCategory()
                .toArray(new MovieItem[data.getMoviesInCategory().size()]));
        moviesTitle.setText(data.getCategoryName());
        moviesPanel.setVisible(true);
    }

    @Override
    public void onShowMovie(ActionShowMovie data) {
        singleMovieTitle.setText(data.getMovie().getName());
        movieDescription.setText(data.getMovie().getDescription() + "\nBudget: " + data.getMovie().getBudget() + "\nYear:" + data.getMovie().getYear());
        moviePanel.setVisible(true);
    }

    private void initCategoriesList() {
        JPanel categoriesPanel = new JPanel(new BorderLayout());
        JLabel categoriesLabel = new JLabel("Categories");
        JLabel deleteLabel = new JLabel("( Delete");
        JLabel editLabel = new JLabel("|Edit )");
        JPanel editLayout = new JPanel();
        editLayout.setLayout(new BoxLayout(editLayout, BoxLayout.X_AXIS));
        editLayout.add(deleteLabel);
        editLayout.add(editLabel);
        categoriesPanel.add(editLayout, BorderLayout.SOUTH);
        categoriesLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        categoriesList = new JList<CategoryItem>();
        categoriesList.setCellRenderer(new CategoryRenderer());
        categoriesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane categoriesScroll = new JScrollPane(categoriesList);
        categoriesScroll.setBorder(new EmptyBorder(0, 10, 0, 10));
        categoriesPanel.add(categoriesScroll, BorderLayout.CENTER);
        categoriesPanel.add(categoriesLabel, BorderLayout.NORTH);
        categoriesPanel.setMaximumSize(new Dimension(120, Integer.MAX_VALUE));
        viewPanel.add(categoriesPanel);
        categoriesList.addListSelectionListener(e -> {
            controller().requestCategory(categoriesList.getSelectedValue().getId());
        });
    }

    private void initMoviesList() {

        moviesPanel = new JPanel(new BorderLayout());
        moviesPanel.setVisible(false);
        moviesTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
        JLabel deleteLabel = new JLabel("( Delete");
        JLabel editLabel = new JLabel("|Edit )");
        JPanel editLayout = new JPanel();
        editLayout.setLayout(new BoxLayout(editLayout, BoxLayout.X_AXIS));
        editLayout.add(deleteLabel);
        editLayout.add(editLabel);
        moviesPanel.add(editLayout, BorderLayout.SOUTH);

        moviesList = new JList<MovieItem>();
        moviesList.setCellRenderer(new MovieRenderer());
        JScrollPane moviesScroll = new JScrollPane(moviesList);
        moviesPanel.add(moviesScroll, BorderLayout.CENTER);
        moviesPanel.add(moviesTitle, BorderLayout.NORTH);
        moviesPanel.setMaximumSize(new Dimension(120, Integer.MAX_VALUE));
        moviesPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        moviesScroll.setBorder(new EmptyBorder(0, 10, 0, 10));
        viewPanel.add(moviesPanel);
        moviesList.addListSelectionListener(e -> {
            if (moviesList.getSelectedValue() != null) {
                controller().requestMovie(moviesList.getSelectedValue().getId());
            }
        });

    }

    private void initSingleMoviePage() {
        moviePanel = new JPanel(new BorderLayout());
        moviePanel.setVisible(false);
        JPanel movieContentPanel = new JPanel(new BorderLayout());

        movieContentPanel.setBackground(Color.WHITE);
        singleMovieTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
        movieDescription.setEditable(false);
        movieContentPanel.add(movieDescription, BorderLayout.NORTH);
        moviePanel.add(singleMovieTitle, BorderLayout.NORTH);
        moviePanel.add(movieContentPanel, BorderLayout.CENTER);
        viewPanel.add(moviePanel);

    }

}
