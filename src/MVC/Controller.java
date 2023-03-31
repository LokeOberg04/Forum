package MVC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;

public class Controller extends JFrame {

    Model model;
    View View;

    public Controller(Model m, View v) {

        this.model = m;
        this.View = v;
        this.setTitle("Client");


        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\loke.oberg\\IdeaProjects\\ChatProgram\\src\\resources\\B).png");
        this.setIconImage(icon);


        this.setContentPane(View.getPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Model m = new Model();
        View v = new View();
        Controller thisIsTheProgram = new Controller(m, v);
        thisIsTheProgram.setVisible(true);


        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://db.umea-ntig.se:3306/te20? "+
                    "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",m.getuser(),m.getpassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            Statement stmt = conn.createStatement();
            String SQLQuery = "SELECT * FROM lo28forum";
            ResultSet result = stmt.executeQuery(SQLQuery);

            ResultSetMetaData metadata = result.getMetaData();

            int numCols = metadata.getColumnCount();
            for (int i = 1 ; i <= numCols ; i++) {
                System.out.println(metadata.getColumnClassName(i));
            }

            while (result.next()) {
                String output = "";
                output += result.getInt("id") + ", " +
                        result.getString("title") + ", " +
                        result.getString("content") + ", " +
                        result.getTimestamp("createdAt") + ", " +
                        result.getInt("authorId");
                System.out.println(output);
                m.addtoforum(output);
                v.setMessage(m.getforum());
            }

            // insert

            // Scanner in = new Scanner(System.in);
            // System.out.println("Ange namn:");
            // String name = in.nextLine();
            // System.out.println("Ange lösenord:");
            // String password2 = in.nextLine();
//
            // for(int i = 0; i<1; i++) {
            //     SQLQuery = "INSERT INTO fmusers2(name,password) VALUES ('" + name + "', '" + password2 + "')";
            //     stmt.executeUpdate(SQLQuery);
            // }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

