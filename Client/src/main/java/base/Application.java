package base;

import javax.swing.*;
import java.awt.*;

public abstract class Application {
    public Application() {
        // Construct the main frame of the application.
        JFrame frame = new JFrame();
frame.setMinimumSize(new Dimension(500,200));
        // Exit the application when the main frame is closed.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Start the application.
        this.start(frame);

        // Pack the application frame.
        frame.pack();

        // Make the application frame visible.
        frame.setVisible(true);
    }

    protected abstract void start(final JFrame frame);
}
