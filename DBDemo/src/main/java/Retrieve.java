import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Retrieve {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JLabel label1 = new JLabel("Name: ");
        JLabel label2 = new JLabel("Email: ");
        JTextField text1 = new JTextField(20);
        JTextField text2 = new JTextField(20);
        Dotenv dotenv = Dotenv.load();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dotenv.get("DB_TEST"),
                    dotenv.get("USERNAME"),
                    dotenv.get("PASSWORD"));
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from data where id=3333");
            String name = "",
                    email = "";
            if (rs.next()) {
                name = rs.getString("name");
                email = rs.getString("email");
            }
            text1.setText(name);
            text2.setText(email);
        } catch (Exception e) {}

        JPanel p = new JPanel(new GridLayout(2, 2));
        p.add(label1);
        p.add(text1);
        p.add(label2);
        p.add(text2);
        f.add(p);
        f.setVisible(true);
        f.pack();
    }
}
