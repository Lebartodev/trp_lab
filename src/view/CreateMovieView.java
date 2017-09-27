package view;

import base.View;
import controller.MainController;
import model.MainModel;

import javax.swing.*;
import java.awt.*;

public class CreateMovieView extends View<MainModel, MainController> {
    @Override
    public JComponent render() {
        JPanel viewPanel = new JPanel(new BorderLayout());

        return viewPanel;
    }
}
