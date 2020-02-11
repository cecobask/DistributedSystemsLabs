import javax.swing.*;
import java.awt.*;

public class View {

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
    private JTextArea addressTextArea;
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

    public View(String title) {
        // Create components.
        frame = new JFrame(title);
        panel = new JPanel(new GridLayout(9, 2));
        ssnLabel = new JLabel("SSN");
        dobLabel = new JLabel("DoB");
        nameLabel = new JLabel("Name");
        addressLabel = new JLabel("Address");
        salaryLabel = new JLabel("Salary");
        genderLabel = new JLabel("Gender");
        ssnTextField = new JTextField();
        dobTextField = new JTextField();
        nameTextField = new JTextField();
        addressTextArea = new JTextArea();
        salaryTextField = new JTextField();
        maleRadioButton = new JRadioButton();
        femaleRadioButton = new JRadioButton();
        genderGroup = new ButtonGroup();
        nextButton = new JButton("Next");
        previousButton = new JButton("Previous");
        clearButton = new JButton("Clear");
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");

        // Add radio buttons to a group.
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        maleRadioButton.setSelected(true);

        // Create an ArrayList of components and them to the panel.
        addComponents(
                panel,
                new JComponent[]{ssnLabel, ssnTextField, dobLabel, dobTextField, nameLabel, nameTextField,
                        addressLabel, addressTextArea, salaryLabel, salaryTextField, genderLabel, maleRadioButton,
                        femaleRadioButton, previousButton, clearButton, nextButton, addButton, deleteButton, updateButton}
        );

        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }

    private void addComponents(JComponent panel, JComponent[] components) {
        // Loop over the ArrayList of components and add to panel.
        for (JComponent component : components) {
            panel.add(component);
        }
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

    public JTextArea getAddressTextArea() {
        return addressTextArea;
    }

    public void setAddressTextArea(JTextArea addressTextArea) {
        this.addressTextArea = addressTextArea;
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
