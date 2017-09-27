import base.Application;
import controller.MainController;
import model.MainModel;
import view.CategoriesView;
import view.MenuView;

import javax.swing.*;

public class MovieApplication extends Application {

    protected void start(final JFrame frame) {
        MainModel model=new MainModel();
        MainController controller=new MainController();
        frame.setJMenuBar(new MenuView(model,controller).render());
        frame.setTitle("Movie Library");
        frame.getContentPane().add(new CategoriesView(model,controller).render());
    }

    public static void main(String[] args) {
        new MovieApplication();
    }
}
