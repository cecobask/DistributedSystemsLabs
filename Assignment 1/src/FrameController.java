import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class FrameController implements ActionListener {

    private JTextField ssnTextField;
    private JTextField dobTextField;
    private JTextField nameTextField;
    private JTextField addressTextField;
    private JTextField salaryTextField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private ButtonGroup genderGroup;
    private JButton previousButton;
    private JButton clearButton;
    private JButton nextButton;
    private JButton deleteButton;
    private JButton addButton;
    private JButton updateButton;
    private Employee employee = new Employee("", new Date(0), "", "", 0, "");
    private ArrayList<Employee> employees = new ArrayList<>();
    private final EmployeeService employeeService = new EmployeeService();
    private int currentIndex = 0;

    public FrameController(Frame frame) {
        initComponents(frame);
        addActionListeners( // Add click listeners to the buttons.
                new JButton[]{previousButton, clearButton, nextButton, deleteButton, addButton, updateButton}
        );
        loadData();
    }

    private void initComponents(Frame frame) {
        ssnTextField = frame.getSsnTextField();
        dobTextField = frame.getDobTextField();
        nameTextField = frame.getNameTextField();
        addressTextField = frame.getAddressTextField();
        salaryTextField = frame.getSalaryTextField();
        maleRadioButton = frame.getMaleRadioButton();
        femaleRadioButton = frame.getFemaleRadioButton();
        genderGroup = frame.getGenderGroup();
        previousButton = frame.getPreviousButton();
        clearButton = frame.getClearButton();
        nextButton = frame.getNextButton();
        deleteButton = frame.getDeleteButton();
        addButton = frame.getAddButton();
        updateButton = frame.getUpdateButton();
        dobTextField.setToolTipText("dd/mm/yyyy");
    }

    private void addActionListeners(JButton[] buttons) {
        for (JButton button : buttons) {
            button.addActionListener(this);
        }
    }

    private void loadData() {
        employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            enableDisableButtons(new JButton[0], new JButton[]{deleteButton, previousButton, nextButton, updateButton});
            clearButton.doClick(); // Clear input boxes.
            showMessageDialog("The table is empty!");
            employee = null;
            return;
        }

        currentIndex = 0; // Reset index.
        employee = employees.get(currentIndex); // Currently visible employee.

        nextButton.setEnabled(!(employees.size() <= 1)); // Disable 'NEXT' button when there are 0 or 1 employees in the database.
        enableDisableButtons(new JButton[]{deleteButton, updateButton}, new JButton[]{previousButton});
        displayData();
    }

    private void displayData() {
        ssnTextField.setText(employee.getSsn());
        dobTextField.setText(employee.getDob().equals(new Date(0)) ? "" : employee.getFormattedDate());
        nameTextField.setText(employee.getName());
        addressTextField.setText(employee.getAddress().equals("NOT_PROVIDED") ? "" : employee.getAddress());
        salaryTextField.setText(employee.getSalary() == 0 ? "" : String.valueOf(employee.getSalary()));
        femaleRadioButton.setSelected(employee.getGender().equals("F"));
        maleRadioButton.setSelected(employee.getGender().equals("M"));
    }

    private void enableDisableButtons(JButton[] enabledButtons, JButton[] disabledButtons) {
        for (JButton button : enabledButtons) button.setEnabled(true);
        for (JButton button : disabledButtons) button.setEnabled(false);
    }

    private void addEmployee(Employee employee) {
        try {
            constructEmployeeAttributes(employee);
            employeeService.addEmployee(employee);
        } catch (NumberFormatException ex) {
            showMessageDialog("Problem with field 'SALARY'. Please provide integer value.");
            return;
        } catch (ParseException ex) {
            showMessageDialog("'DOB' must be a valid date of format: dd/mm/yyyy.");
            return;
        } catch (SQLException ex) {
            showMessageDialog("Employee with SSN '" + employee.getSsn() + "' already exists.");
            return;
        } catch (NullPointerException ex) {
            showMessageDialog("All fields must be filled out.");
            return;
        }

        // If all validations pass, show a successful message to the user.
        showMessageDialog("Added employee with SSN: '" + employee.getSsn() + "'.");
        loadData();
    }

    private void deleteEmployee(String ssn) {
        boolean result = employeeService.deleteEmployee(ssn);
        if (!result) {
            showMessageDialog("Could not find employee with SSN: '" + ssn + "'.");
            return;
        }

        showMessageDialog("Deleted employee with SSN: '" + ssn + "'.");
        loadData();
    }

    private void updateEmployee(Employee employee) {
        try {
            constructEmployeeAttributes(employee);
        } catch (NumberFormatException ex) {
            showMessageDialog("Problem with field 'SALARY'. Please provide integer value.");
            return;
        } catch (ParseException ex) {
            showMessageDialog("'DOB' must be a valid date of format: dd/mm/yyyy.");
            return;
        } catch (NullPointerException ex) {
            showMessageDialog("All fields must be filled out.");
            return;
        }

        boolean result = employeeService.updateEmployee(employee);
        if (!result) {
            showMessageDialog("Could not find employee with SSN: '" + employee.getSsn() + "'.");
            return;
        }

        showMessageDialog("Updated employee with SSN: '" + employee.getSsn() + "'.");
        loadData();
    }

    private void constructEmployeeAttributes(Employee employee) throws ParseException {
        employee.setSsn(ssnTextField.getText().trim());
        employee.setDob(employee.parseDobString(dobTextField.getText().trim()));
        employee.setName(nameTextField.getText().trim());
        employee.setAddress(addressTextField.getText().trim());
        employee.setSalary(Integer.parseInt(salaryTextField.getText().trim()));
        employee.setGender(genderGroup.getSelection().getActionCommand());
    }

    private void clearInputs() {
        ssnTextField.setText("");
        dobTextField.setText("");
        nameTextField.setText("");
        addressTextField.setText("");
        salaryTextField.setText("");
        genderGroup.clearSelection();
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private int confirmDeletion() {
        // 0=yes, 1=no
        return JOptionPane.showConfirmDialog(null,
                "Do you want to proceed?",
                "Delete current employee",
                JOptionPane.YES_NO_OPTION
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 'PREVIOUS' button clicked.
        if (e.getSource() == previousButton) {
            currentIndex--; // Decrement by 1.
            previousButton.setEnabled(currentIndex != 0); // Disable 'PREVIOUS' button if the shown employee is the first in the list.
            nextButton.setEnabled(true);
            employee = employees.get(currentIndex);
            displayData();
        }
        // 'CLEAR' button clicked.
        else if (e.getSource() == clearButton) {
            clearInputs();
        }
        // 'NEXT' button clicked.
        else if (e.getSource() == nextButton) {
            currentIndex++; // Increment by 1.
            nextButton.setEnabled(currentIndex != employees.size() - 1); // Disable 'NEXT' button if there are no more employees in the list.
            previousButton.setEnabled(true);
            employee = employees.get(currentIndex);
            displayData();
        }
        // 'DELETE' button clicked.
        else if (e.getSource() == deleteButton) {
            if (ssnTextField.getText().equals("")) { // Check if 'SSN' field is empty.
                showMessageDialog("Please provide SSN.");
                return;
            }
            int result = confirmDeletion();
            switch (result) {
                case 0: // Clicked 'Yes'.
                    deleteEmployee(ssnTextField.getText()); // Delete employee with specified SSN.
                    break;
                case 1: // Clicked 'No'.
                    break;
            }
        }
        // 'ADD' button clicked.
        else if (e.getSource() == addButton) {
            addEmployee(new Employee());
        }
        // 'UPDATE' button clicked.
        else if (e.getSource() == updateButton) {
            if (ssnTextField.getText().equals("")) { // Check if 'SSN' field is empty.
                showMessageDialog("Please provide SSN.");
                return;
            }
            updateEmployee(new Employee());
        }
    }
}
