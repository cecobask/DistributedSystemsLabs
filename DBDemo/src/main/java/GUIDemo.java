import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GUIDemo implements ActionListener {

    private JTextField idTxt;
    private JTextField fNameTxt;
    private JTextField lNameTxt;
    private JTextField emailTxt;
    private JButton nextBtn;
    private JButton prevBtn;
    private ResultSet data;

    private void GUITest() {
        // Initialise widgets.
        JFrame frame = new JFrame();
        JLabel idLbl = new JLabel("ID");
        JLabel fNameLbl = new JLabel("First Name");
        JLabel lNameLbl = new JLabel("Last Name");
        JLabel emailLbl = new JLabel("Email");
        JPanel panel = new JPanel(new GridLayout(5, 2));
        idTxt = new JTextField(20);
        fNameTxt = new JTextField(20);
        lNameTxt = new JTextField(20);
        emailTxt = new JTextField(20);
        nextBtn = new JButton("Next");
        prevBtn = new JButton("Previous");

        // Click listeners.
        nextBtn.addActionListener(this);
        prevBtn.addActionListener(this);

        // Loads the environment variables from .env file.
        Dotenv dotenv = Dotenv.load();

        try {
            // Load the data from specified table.
            data = loadData(
                    dotenv.get("DB_TEST"),
                    dotenv.get("TABLE_NAME"),
                    dotenv.get("USERNAME"),
                    dotenv.get("PASSWORD")
            );

            // Only load the GUI if the table is not empty.
            if (data != null) {
                // Create an ArrayList of components and them to the panel.
                addComponents(
                        panel,
                        new JComponent[]{idLbl, idTxt, fNameLbl, fNameTxt, lNameLbl, lNameTxt, emailLbl, emailTxt, prevBtn, nextBtn}
                );

                frame.add(panel);
                frame.setVisible(true);
                frame.pack();

                // On start, the GUI will display the first element, therefore previous button must be disabled.
                prevBtn.setEnabled(false);

                // Display the loaded data in the widgets.
                displayData();
            } else {
                System.out.println("Table '" + dotenv.get("TABLE_NAME") + "' is empty!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ResultSet loadData(String dbName, String tableName, String userName, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName,
                userName,
                password
        );
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from " + tableName);

        return rs.next() ? rs : null;
    }

    private void addComponents(JComponent panel, JComponent[] components) {
        // Loop over the ArrayList of components and add to panel.
        for (JComponent component : components) {
            panel.add(component);
        }
    }

    private void displayData() throws SQLException {
        // Set values to widgets.
        idTxt.setText(String.valueOf(data.getInt("id")));
        fNameTxt.setText(data.getString("name"));
        lNameTxt.setText(data.getString("lastname"));
        emailTxt.setText(data.getString("email"));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            // 'Next' button pressed.
            if (event.getSource() == nextBtn) {
                // Check whether next entry exists in the DB.
                if (data.next()) {
                    prevBtn.setEnabled(true);
                    displayData();
                    // Disable the 'Next' button if next entry doesn't exist.
                    if (data.isLast()) {
                        nextBtn.setEnabled(false);
                    }
                }
            }
            // 'Previous' button pressed.
            else if (event.getSource() == prevBtn) {
                // Check whether previous entry exists in the DB.
                if (data.previous()) {
                    nextBtn.setEnabled(true);
                    displayData();
                    // Disable the 'Previous' button if previous entry doesn't exist.
                    if (data.isFirst()) {
                        prevBtn.setEnabled(false);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        GUIDemo app = new GUIDemo();
        app.GUITest();
    }
}
