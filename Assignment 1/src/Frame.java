import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class Frame {

    // Declare Swing components.
    private JFrame frame;
    private JPanel panel;
    private JLabel ssnLabel;
    private JLabel dobLabel;
    private JLabel nameLabel;
    private JLabel addressLabel;
    private JLabel salaryLabel;
    private JLabel genderLabel;
    private JTextField ssnTextField;
    private JTextField dobTextField;
    private JTextField nameTextField;
    private JTextField addressTextField;
    private JTextField salaryTextField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private ButtonGroup genderGroup;
    private JButton nextButton;
    private JButton previousButton;
    private JButton clearButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    public Frame(String title) {
        frame = new JFrame(title); // Set frame title.

        createComponents(); // Component declarations.

        // Dynamically add all components to the panel.
        addComponents(
                panel,
                new JComponent[]{ssnLabel, dobLabel, nameLabel, addressLabel, salaryLabel, genderLabel},
                new JComponent[]{ssnTextField, dobTextField, nameTextField, addressTextField, salaryTextField},
                new JComponent[]{maleRadioButton, femaleRadioButton},
                new JComponent[]{previousButton, clearButton, nextButton, deleteButton, addButton, updateButton}
        );
    }

    private void addComponents(JPanel panel, JComponent[] labels, JComponent[] inputs, JComponent[] radioButtons, JComponent[] buttons) {
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5; // Spacing.
        gbc.ipadx = 15; // Set padding.
        gbc.ipady = 15;

        // Add radio buttons to a group.
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        maleRadioButton.setSelected(true);

        gbc.gridx = 0; // Point at first column.
        gbc.gridwidth = 1;
        for (int y = 0; y < labels.length; y++) {
            gbc.gridy = y;
            panel.add(labels[y], gbc); // Dynamically add label components.
        }

        gbc.gridx = 1; // Point at second column.
        gbc.gridwidth = 3;
        for (int y = 0; y < inputs.length; y++) {
            gbc.gridy = y;
            panel.add(inputs[y], gbc); // Dynamically add input components.
        }

        gbc.gridy = 5; // Point at sixth row.
        gbc.gridwidth = 1; // Each element takes up 1 cell width.
        gbc.weightx = 0; // No spacing between radio buttons.
        for (int x = 0; x < radioButtons.length; x++) {
            gbc.gridx = x+1; // Position starting at 1.
            panel.add(radioButtons[x], gbc); // Dynamically add radio buttons.
        }

        gbc.weightx = 0.5;
        for (int x = 0; x < buttons.length; x++) {
            gbc.gridy = x <= 2 ? 6 : 7; // First 3 buttons on 6th row, the remaining - on the 7th.
            gbc.gridx = GridBagConstraints.RELATIVE; // Relative position.
            panel.add(buttons[x], gbc); // // Dynamically add buttons.
        }

        // Create a border for the panel.
        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        new EmptyBorder(10, 10, 10, 10),
                        new EtchedBorder()
                )
        );

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents() {
        panel = new JPanel(new GridBagLayout());
        ssnLabel = new JLabel("SSN: ");
        dobLabel = new JLabel("DOB: ");
        nameLabel = new JLabel("NAME: ");
        addressLabel = new JLabel("ADDRESS: ");
        salaryLabel = new JLabel("SALARY: ");
        genderLabel = new JLabel("GENDER: ");
        ssnTextField = new JTextField(20);
        dobTextField = new JTextField(20);
        nameTextField = new JTextField(20);
        addressTextField = new JTextField(20);
        salaryTextField = new JTextField(20);
        maleRadioButton = new JRadioButton("M");
        femaleRadioButton = new JRadioButton("F");
        genderGroup = new ButtonGroup();
        nextButton = new JButton("NEXT");
        previousButton = new JButton("PREVIOUS");
        clearButton = new JButton("CLEAR");
        addButton = new JButton("ADD");
        deleteButton = new JButton("DELETE");
        updateButton = new JButton("UPDATE");

        maleRadioButton.setActionCommand("M");
        femaleRadioButton.setActionCommand("F");
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getSsnLabel() {
        return ssnLabel;
    }

    public void setSsnLabel(JLabel ssnLabel) {
        this.ssnLabel = ssnLabel;
    }

    public JLabel getDobLabel() {
        return dobLabel;
    }

    public void setDobLabel(JLabel dobLabel) {
        this.dobLabel = dobLabel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JLabel getAddressLabel() {
        return addressLabel;
    }

    public void setAddressLabel(JLabel addressLabel) {
        this.addressLabel = addressLabel;
    }

    public JLabel getSalaryLabel() {
        return salaryLabel;
    }

    public void setSalaryLabel(JLabel salaryLabel) {
        this.salaryLabel = salaryLabel;
    }

    public JLabel getGenderLabel() {
        return genderLabel;
    }

    public void setGenderLabel(JLabel genderLabel) {
        this.genderLabel = genderLabel;
    }

    public JTextField getSsnTextField() {
        return ssnTextField;
    }

    public void setSsnTextField(JTextField ssnTextField) {
        this.ssnTextField = ssnTextField;
    }

    public JTextField getDobTextField() {
        return dobTextField;
    }

    public void setDobTextField(JTextField dobTextField) {
        this.dobTextField = dobTextField;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public JTextField getAddressTextField() {
        return addressTextField;
    }

    public void setAddressTextField(JTextField addressTextField) {
        this.addressTextField = addressTextField;
    }

    public JTextField getSalaryTextField() {
        return salaryTextField;
    }

    public void setSalaryTextField(JTextField salaryTextField) {
        this.salaryTextField = salaryTextField;
    }

    public JRadioButton getMaleRadioButton() {
        return maleRadioButton;
    }

    public void setMaleRadioButton(JRadioButton maleRadioButton) {
        this.maleRadioButton = maleRadioButton;
    }

    public JRadioButton getFemaleRadioButton() {
        return femaleRadioButton;
    }

    public void setFemaleRadioButton(JRadioButton femaleRadioButton) {
        this.femaleRadioButton = femaleRadioButton;
    }

    public ButtonGroup getGenderGroup() {
        return genderGroup;
    }

    public void setGenderGroup(ButtonGroup genderGroup) {
        this.genderGroup = genderGroup;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public void setNextButton(JButton nextButton) {
        this.nextButton = nextButton;
    }

    public JButton getPreviousButton() {
        return previousButton;
    }

    public void setPreviousButton(JButton previousButton) {
        this.previousButton = previousButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public void setClearButton(JButton clearButton) {
        this.clearButton = clearButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }
}
