package main.java.view;

import main.java.ClientModel;
import main.java.base.View;
import main.java.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuView extends View<MainController> {
    private JFrame frame;

    public MenuView(MainController controller, JFrame frame) {
        this.controller(controller);
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
            frame.getContentPane().add(new CreateCategoryView(controller().model(), frame).render());
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
        });
        createMovie.addActionListener(e -> {
            JFrame frame = new JFrame("Create movie");
            frame.setSize(300, 100);
            frame.setMinimumSize(new Dimension(300, 100));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(new CreateMovieView(controller(), frame).render());
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
        });

        return menuBar;
    }
}
