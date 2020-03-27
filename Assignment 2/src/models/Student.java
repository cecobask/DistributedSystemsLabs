package models;

/**
 * @author Tsvetoslav Dimov
 * Student ID: 20077038
 * Module: Distributed Systems
 */
public class Student {

    // Student object fields.
    private int SID;
    private int studID;
    private String firstName;
    private String lastName;

    /**
     * Object constructor, used for generating instances of Student.
     * @param SID Auto-increment value
     * @param studID Student ID
     * @param firstName First name of the student
     * @param lastName Last name of the student
     */
    public Student(int SID, int studID, String firstName, String lastName) {
        this.SID = SID;
        this.studID = studID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Student object toString() implementation.
     * @return String
     */
    @Override
    public String toString() {
        return "Student{" +
                "SID=" + SID +
                ", studID=" + studID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    /**
     * Empty constructor, used for assigning values to fields after object creation.
     */
    public Student() {}

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public int getStudID() {
        return studID;
    }

    public void setStudID(int studID) {
        this.studID = studID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
