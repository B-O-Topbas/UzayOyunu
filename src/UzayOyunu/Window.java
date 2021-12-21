package UzayOyunu;

import javax.swing.*;
public class Window extends JFrame {
    Panel p=new Panel();
    public Window(){
        setSize(600,400);
        add(p);
        setResizable(false);
        setLocationRelativeTo(null);
        setFocusable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Window();
    }
}
