package view;

import model.CategoryItem;

import javax.swing.*;
import java.awt.*;

public class CategoryRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        CategoryItem object = (CategoryItem) value;
        setText(object.getName());

        return this;
    }
}
