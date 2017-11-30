package main.java;

import main.java.base.Application;
import main.java.controller.MainController;
import main.java.view.CategoriesView;
import main.java.view.MenuView;

import javax.swing.*;

public class MovieApplication extends Application {

    protected void start(final JFrame frame) {
        ClientModel model=new ClientModel();
        MainController controller=new MainController();
        frame.setJMenuBar(new MenuView(model,controller,frame).render());
        frame.setTitle("Movie Library");
        frame.getContentPane().add(new CategoriesView(model,controller,frame).render());
    }

    public static void main(String[] args) {
        new MovieApplication();
    }
}