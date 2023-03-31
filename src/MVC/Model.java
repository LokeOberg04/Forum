package MVC;

import javax.swing.*;

public class Model {
    String user = "te20";

    String forum = "";
    JPasswordField pf = new JPasswordField();
    //String password = new String(pf.getPassword());

    String password = JOptionPane.showInputDialog(pf, "password");

    public void addtoforum(String c) {
        forum += c + "\n";
    }

    public String getforum() {
        return forum;
    }
    public String getpassword() {
    return password;
    }

    public String getuser() {
        return user;
    }

}