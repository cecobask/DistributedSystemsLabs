import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GUIDemo implements ActionListener, DocumentListener {

    private JTextField idTxt;
    private JTextField fNameTxt;
    private JTextField lNameTxt;
    private JTextField emailTxt;
    private JButton nextBtn;
    private JButton prevBtn;
    private JButton clearBtn;
    private JButton deleteBtn;
    private JButton addBtn;
    private ResultSet data;
    private Dotenv dotenv;
    private Connection conn;
    private boolean tableIsEmpty;

    private void GUITest() {
        // Initialise widgets.
        JFrame frame = new JFrame();
        JLabel idLbl = new JLabel("ID");
        JLabel fNameLbl = new JLabel("First Name");
        JLabel lNameLbl = new JLabel("Last Name");
        JLabel emailLbl = new JLabel("Email");
        JPanel panel = new JPanel(new GridLayout(7, 2));
        idTxt = new JTextField(20);
        fNameTxt = new JTextField(20);
        lNameTxt = new JTextField(20);
        emailTxt = new JTextField(20);
        nextBtn = new JButton("Next");
        prevBtn = new JButton("Previous");
        clearBtn = new JButton("Clear");
        deleteBtn = new JButton("Delete");
        addBtn = new JButton("Add");

        // Action listeners.
        nextBtn.addActionListener(this);
        prevBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        addBtn.addActionListener(this);

        // Document listeners.
        idTxt.getDocument().addDocumentListener(this);

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
                            prevBtn, nextBtn, clearBtn, deleteBtn, addBtn}
            );

            frame.add(panel);
            frame.setVisible(true);
            frame.pack();

            // Display the loaded data in the widgets.
            displayData();
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
            tableIsEmpty = true;
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

        tableIsEmpty = false;

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
            int result = st.executeUpdate("DELETE FROM " + table + " WHERE id=" + id);

            if (result == 0) {
                showMessageDialog("Record with ID '" + id + "' not found!");
                return;
            }

            // Show what was deleted.
            showMessageDialog("Deleted user with ID: " + idTxt.getText());

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

    private void addRecord(String table, int id, String name, String lastname, String email) {
        try {
            // Execute DELETE query.
            Statement st = conn.createStatement();
            st.executeUpdate(
                    "INSERT INTO " + table + " VALUES (" + id + ", '" + name + "', '" + lastname + "', '" + email + "')"
            );

            showMessageDialog("Successfully inserted new record into table '" + dotenv.get("TABLE_NAME") + "'");

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
            showMessageDialog("There was a problem inserting this record.");
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
                int result = confirmDeletion();
                switch (result) {
                    // Clicked 'Yes'.
                    case 0:
                        // Check if 'ID' field is of integer format.
                        if (validIdFormat()) {
                            // Delete current row from the table.
                            deleteRecord(dotenv.get("TABLE_NAME"), Integer.parseInt(idTxt.getText()));
                        }
                        break;
                    // Clicked 'No'.
                    case 1:
                        break;
                }
            }
            // 'Add' button pressed.
            else if (event.getSource() == addBtn) {
                // Check if 'ID' field is of integer format.
                if (validIdFormat()) {
                    // Insert a record into specified table.
                    addRecord(
                            dotenv.get("TABLE_NAME"), Integer.parseInt(idTxt.getText()),
                            fNameTxt.getText(), lNameTxt.getText(), emailTxt.getText()
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private boolean validIdFormat() {
        try {
            Integer.parseInt(idTxt.getText());
            return true;
        } catch (NumberFormatException e) {
            // Invalid format.
            showMessageDialog("'ID' field must be of integer format!");
            return false;
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        enableDisableButtons(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        enableDisableButtons(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    // Enable/Disable buttons based on length of 'ID' input box.
    public void enableDisableButtons(DocumentEvent e) {
        Document doc = e.getDocument();
        // Text input field is empty.
        if (doc.getLength() == 0 && !tableIsEmpty) {
            clearBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            addBtn.setEnabled(false);
        } else if (doc.getLength() == 0 && tableIsEmpty) {
            clearBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            addBtn.setEnabled(false);
        } else if (doc.getLength() > 0 && tableIsEmpty) {
            clearBtn.setEnabled(true);
            deleteBtn.setEnabled(false);
            addBtn.setEnabled(true);
        } else {
            clearBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            addBtn.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        GUIDemo app = new GUIDemo();
        app.GUITest();
    }
}
