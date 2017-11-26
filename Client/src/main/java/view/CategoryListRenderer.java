package main.java.view;

import main.java.model.CategoryItem;

import javax.swing.*;
import java.awt.*;

class CategoryListRenderer implements ListCellRenderer {
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);
        CategoryItem object = (CategoryItem) value;
        renderer.setText(object.getName());
        return renderer;
    }
}
