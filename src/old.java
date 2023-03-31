import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class old {
    public static void main(String[] args) {
        Connection conn = null;
        String user = "te20";
        JPasswordField pf = new JPasswordField();
        JOptionPane.showConfirmDialog(null, pf, "password?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        String password = new String(pf.getPassword());

        try {
            conn = DriverManager.getConnection("jdbc:mysql://db.umea-ntig.se:3306/te20? "+
                    "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            Statement stmt = conn.createStatement();
            String SQLQuery = "SELECT * FROM lo28users";
            ResultSet result = stmt.executeQuery(SQLQuery);

            ResultSetMetaData metadata = result.getMetaData();

            int numCols = metadata.getColumnCount();
            for (int i = 1 ; i <= numCols ; i++) {
                System.out.println(metadata.getColumnClassName(i));
            }

            while (result.next()) {
                String output = "";
                output += result.getInt("id") + ", " +
                        result.getString("name") + ", " +
                        result.getString("password");
                System.out.println(output);
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
