package view;

import model.CategoryItem;
import model.MovieItem;

import javax.swing.*;
import java.awt.*;

public class MovieRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        MovieItem object = (MovieItem) value;
        setText(object.getName());

        return this;
    }
}
