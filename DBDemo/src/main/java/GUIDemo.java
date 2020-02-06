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
    private JButton clearBtn;
    private JButton deleteBtn;
    private ResultSet data;
    private Dotenv dotenv;
    private Connection conn;

    private void GUITest() {
        // Initialise widgets.
        JFrame frame = new JFrame();
        JLabel idLbl = new JLabel("ID");
        JLabel fNameLbl = new JLabel("First Name");
        JLabel lNameLbl = new JLabel("Last Name");
        JLabel emailLbl = new JLabel("Email");
        JPanel panel = new JPanel(new GridLayout(6, 2));
        idTxt = new JTextField(20);
        fNameTxt = new JTextField(20);
        lNameTxt = new JTextField(20);
        emailTxt = new JTextField(20);
        nextBtn = new JButton("Next");
        prevBtn = new JButton("Previous");
        clearBtn = new JButton("Clear");
        deleteBtn = new JButton("Delete");

        // Click listeners.
        nextBtn.addActionListener(this);
        prevBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        deleteBtn.addActionListener(this);

        // Loads the environment variables from .env file.
        dotenv = Dotenv.load();

        try {
            // Load the data from specified table.
            data = loadData(
                    dotenv.get("DB_TEST"),
                    dotenv.get("TABLE_NAME"),
                    dotenv.get("USERNAME"),
                    dotenv.get("PASSWORD")
            );
            // Create an ArrayList of components and them to the panel.
            addComponents(
                    panel,
                    new JComponent[]{idLbl, idTxt, fNameLbl, fNameTxt, lNameLbl, lNameTxt, emailLbl, emailTxt,
                            prevBtn, nextBtn, clearBtn, deleteBtn}
            );

            frame.add(panel);
            frame.setVisible(true);
            frame.pack();

            // Display the loaded data in the widgets.
            displayData();

//            INSERT INTO `data` (`id`, `name`, `lastname`, `email`) VALUES
//            ('1', 'test1', 'test1.', 'test1@gmail.com'),
//            ('2', 'test2', 'test2.', 'test2@gmail.com'),
//            ('3', 'test3', 'test3.', 'test3@gmail.com'),
//            ('4', 'test4', 'test4.', 'test4@gmail.com')
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet loadData(String dbName, String tableName, String userName, String password) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName,
                userName,
                password
        );
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM " + tableName);

        // Check if the table is empty.
        if (!rs.next()) {
            // Disable buttons.
            deleteBtn.setEnabled(false);
            prevBtn.setEnabled(false);
            nextBtn.setEnabled(false);
            // Clear input boxes.
            clearBtn.doClick();
            showMessageDialog("Table '" + dotenv.get("TABLE_NAME") + "' is empty!");
            return null;
        }

        // Count the number of rows in the table.
        int count = 0;
        rs.beforeFirst(); // Point cursor at default position.
        while (rs.next()) {
            count++;
        }

        // Disable 'Next' button if there are 0 or 1 rows in the table.
        if (count <= 1) {
            nextBtn.setEnabled(false);
        } else {
            nextBtn.setEnabled(true);
        }

        // Point cursor at first row and disable 'Previous' button by default.
        rs.first();
        prevBtn.setEnabled(false);
        deleteBtn.setEnabled(true);

        return rs;
    }

    private void addComponents(JComponent panel, JComponent[] components) {
        // Loop over the ArrayList of components and add to panel.
        for (JComponent component : components) {
            panel.add(component);
        }
    }

    private void displayData() throws SQLException {
        if (data != null) {
            // Set values to widgets.
            idTxt.setText(String.valueOf(data.getInt("id")));
            fNameTxt.setText(data.getString("name"));
            lNameTxt.setText(data.getString("lastname"));
            emailTxt.setText(data.getString("email"));
        }
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private int confirmDeletion() {
        // 0=yes, 1=no
        return JOptionPane.showConfirmDialog(null,
                "Do you want to proceed?",
                "Delete current row",
                JOptionPane.YES_NO_OPTION
        );
    }

    private void deleteRecord(String table, int id) {
        try {
            // Execute DELETE query.
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM " + table + " WHERE id=" + id);

            // Show what was deleted.
            String deletedUser = "id=" + idTxt.getText() + ", name=" + fNameTxt.getText() + ", lastname="
                    + lNameTxt.getText() + ", email=" + emailTxt.getText();
            showMessageDialog("Deleted user:\n" + deletedUser);

            // Reload the data.
            data = loadData(
                    dotenv.get("DB_TEST"),
                    dotenv.get("TABLE_NAME"),
                    dotenv.get("USERNAME"),
                    dotenv.get("PASSWORD")
            );
            displayData();
        } catch (SQLException e) {
            e.printStackTrace();
            showMessageDialog("There was a problem deleting this record.");
        }
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
            // 'Clear' button pressed.
            else if (event.getSource() == clearBtn) {
                // Empty all text input fields.
                idTxt.setText("");
                fNameTxt.setText("");
                lNameTxt.setText("");
                emailTxt.setText("");
            }
            // 'Delete' button pressed.
            else if (event.getSource() == deleteBtn) {
                // Check if 'ID' input box is empty.
                if (idTxt.getText().equals("")) {
                    showMessageDialog("ID field must not be empty!");
                    return;
                }
                int result = confirmDeletion();
                switch (result) {
                    // Clicked 'Yes'.
                    case 0:
                        // Delete current row from the table.
                        deleteRecord(dotenv.get("TABLE_NAME"), Integer.parseInt(idTxt.getText()));
                        break;
                    // Clicked 'No'.
                    case 1:
                        break;
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
