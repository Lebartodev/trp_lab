package main.java.view;

import main.java.ClientModel;
import main.java.base.View;
import main.java.controller.MainController;
import main.java.model.CategoryItem;
import main.java.model.MovieItem;
import main.java.model.data.response.ResponseShowCategories;
import main.java.model.data.response.ResponseShowMovie;
import main.java.model.data.response.ResponseShowMovieList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CategoriesView extends View<ClientModel, MainController> {
    private JList<CategoryItem> categoriesList;
    private JList<MovieItem> moviesList;
    private JPanel viewPanel;
    private JLabel moviesTitle = new JLabel("Select a category");
    private JLabel singleMovieTitle = new JLabel("Select movie");
    private JTextArea movieDescription = new JTextArea("asd");
    private JPanel moviesPanel;
    private JPanel moviePanel;
    private JFrame frame;


    public CategoriesView(ClientModel model, MainController controller, JFrame frame) {
        this.setModel(model);
        this.controller(controller);
        this.frame = frame;
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
    public void onShowCategories(ResponseShowCategories data) {
        categoriesList.setListData(data.getCategories().toArray(new CategoryItem[data.getCategories().size()]));

    }

    @Override
    public void onShowSingleCategory(ResponseShowMovieList data) {
        moviesList.setListData(data.getMovies().toArray(new MovieItem[data.getMovies().size()]));
        //TODO:Change
        moviesPanel.setVisible(true);
    }

    @Override
    public void onShowMovie(ResponseShowMovie data) {
        singleMovieTitle.setText(data.getMovie().getName());
        movieDescription.setText(data.getMovie().getDescription() + "\nBudget: " + data.getMovie().getBudget() + "\nYear:" + data.getMovie().getYear());
        moviePanel.setVisible(true);
    }

    private void initCategoriesList() {
        JPanel categoriesPanel = new JPanel(new BorderLayout());
        JLabel categoriesLabel = new JLabel("Categories");
        JButton deleteLabel = new JButton("Delete");
        JButton editLabel = new JButton("Edit");
        deleteLabel.setBorderPainted(false);
        deleteLabel.setMargin(new Insets(0, 5, 0, 5));
        editLabel.setBorderPainted(false);
        editLabel.setMargin(new Insets(0, 5, 0, 5));
        editLabel.setBorder(new EmptyBorder(0, 5, 0, 5));
        deleteLabel.setBorder(new EmptyBorder(0, 5, 0, 5));
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
            if (categoriesList.getSelectedValue() != null) {
                controller().requestCategory(categoriesList.getSelectedValue().getId());
                moviesTitle.setText(categoriesList.getSelectedValue().getName());
            }

        });
        editLabel.addActionListener(e -> {
            if (categoriesList.getSelectedValue() != null) {
                openCategoryEditor();
            }
        });
        deleteLabel.addActionListener(e -> {
            if (categoriesList.getSelectedValue() != null) {
                controller().deleteCategory(categoriesList.getSelectedValue().getId());
                controller().requestCategories();
            }
        });

    }

    private void initMoviesList() {

        moviesPanel = new JPanel(new BorderLayout());
        moviesPanel.setVisible(false);
        moviesTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
        JButton deleteLabel = new JButton("Delete");
        JButton editLabel = new JButton("Edit");
        JPanel editLayout = new JPanel();

        deleteLabel.setBorderPainted(false);
        deleteLabel.setMargin(new Insets(0, 5, 0, 5));
        editLabel.setBorderPainted(false);
        editLabel.setMargin(new Insets(0, 5, 0, 5));
        editLabel.setBorder(new EmptyBorder(0, 5, 0, 5));
        deleteLabel.setBorder(new EmptyBorder(0, 5, 0, 5));
        editLayout.setLayout(new BoxLayout(editLayout, BoxLayout.X_AXIS));
        editLayout.add(deleteLabel);
        editLayout.add(editLabel);
        moviesPanel.add(editLayout, BorderLayout.SOUTH);

        moviesList = new JList<MovieItem>();
        moviesList.setCellRenderer(new MovieRenderer());
        moviesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        deleteLabel.addActionListener(e -> {
            if (moviesList.getSelectedValue() != null) {

                controller().deleteMovie(moviesList.getSelectedValue().getId());
                controller().requestCategory(moviesList.getSelectedValue().getGenreId());
            }
        });
        editLabel.addActionListener(e -> {
            if (moviesList.getSelectedValue() != null) {
                openMovieEditor();
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

    private void openCategoryEditor() {
        JFrame frame = new JFrame("Edit category");
        frame.setSize(300, 100);
        frame.setMinimumSize(new Dimension(300, 100));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new CreateCategoryView(model(), controller(), frame, categoriesList.getSelectedValue().getId()).render());
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(100, 100);
        this.frame.setEnabled(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                CategoriesView.this.frame.setEnabled(true);
            }
        });
    }

    private void openMovieEditor() {
        JFrame frame = new JFrame("Edit movie");
        frame.setSize(300, 100);
        frame.setMinimumSize(new Dimension(300, 100));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new CreateMovieView(model(), controller(), frame, moviesList.getSelectedValue().getId()).render());
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(100, 100);
        this.frame.setEnabled(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                CategoriesView.this.frame.setEnabled(true);
            }
        });
    }

}
