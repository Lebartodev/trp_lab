import base.Application;
import view.CategoriesView;

import javax.swing.*;

public class MovieApplication extends Application {

    protected void start(final JFrame frame) {
        frame.setTitle("Movie Library");
        frame.getContentPane().add(new CategoriesView().render());
    }

    public static void main(String[] args) {
        new MovieApplication();
    }
}
