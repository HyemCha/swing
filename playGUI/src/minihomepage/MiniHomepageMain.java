package minihomepage;

import minihomepage.view.ViewMain;

import javax.swing.*;

public class MiniHomepageMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ViewMain view = new ViewMain();
            }
        });
    }
}
