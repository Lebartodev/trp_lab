import base.Application;
import view.CategoriesView;
import view.MenuView;

import javax.swing.*;

public class MovieApplication extends Application {

    protected void start(final JFrame frame) {
        frame.setJMenuBar(new MenuView().render());
        frame.setTitle("Movie Library");
        frame.getContentPane().add(new CategoriesView().render());
    }

    public static void main(String[] args) {
        new MovieApplication();
    }
}
