package MVC;

import javax.swing.*;
import java.awt.*;

public class View {
    private JTextArea textArea1;
    private JPanel panel1;
    private JButton loginButton;
    private JButton newPostButton;


    public JPanel getPanel() {
        return panel1;
    }

    public void setMessage(String c) {
        textArea1.setText(c);
    }
}
