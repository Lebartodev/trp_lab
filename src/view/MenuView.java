package view;

import base.View;
import controller.MainController;
import model.MainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuView extends View<MainModel, MainController> {

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
            frame.setSize(300,100);
            frame.setMinimumSize(new Dimension(300, 100));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(new CreateCategoryView(model(), controller()).render());
            frame.pack();
            frame.setVisible(true);
            frame.setLocation(100,100);
        });

        return menuBar;
    }
}
