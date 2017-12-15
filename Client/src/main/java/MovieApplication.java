import base.Application;
import controller.MainController;
import view.CategoriesView;
import view.MenuView;

import javax.swing.*;

public class MovieApplication extends Application {

    protected void start(final JFrame frame) {
        ClientModel model=new ClientModel();
        MainController controller=new MainController();
        controller.setModel(model);
        frame.setJMenuBar(new MenuView(controller,frame).render());
        frame.setTitle("Movie Library");
        frame.getContentPane().add(new CategoriesView(controller,frame).render());
    }

    public static void main(String[] args) {
        new MovieApplication();
    }
}
