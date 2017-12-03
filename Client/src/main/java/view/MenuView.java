package main.java.view;

import main.java.base.View;
import main.java.controller.MainController;
import main.java.model.data.response.ResponseStartMovieEdit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuView extends View<MainController> {
    private JFrame frame;
    private MainController controller;

    public MenuView(MainController controller, JFrame frame) {
        this.controller = controller;
        this.frame = frame;
    }

    public JMenuBar render() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem createCategory = new JMenuItem("Create category");
        fileMenu.add(createCategory);
        JMenuItem createMovie = new JMenuItem("Create movie");
        fileMenu.add(createCategory);
        fileMenu.add(createMovie);

        createCategory.addActionListener(e -> {
            JFrame frame = new JFrame("Create category");
            frame.setSize(300, 100);
            frame.setMinimumSize(new Dimension(300, 100));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(new CreateCategoryView(controller(), frame).render());
            frame.pack();
            frame.setVisible(true);
            frame.setLocation(100, 100);
            this.frame.setEnabled(false);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    MenuView.this.frame.setEnabled(true);
                }
            });
        });
        createMovie.addActionListener(e -> {
            controller.requestMovieForCreate();
        });

        return menuBar;
    }

    @Override
    public void openMovieEditor(ResponseStartMovieEdit data) {
        JFrame frame = new JFrame("Create movie");
        frame.setSize(300, 100);
        frame.setMinimumSize(new Dimension(300, 100));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new CreateMovieView(controller(), frame, data).render());
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(100, 100);
        this.frame.setEnabled(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                MenuView.this.frame.setEnabled(true);
            }
        });
    }
}
