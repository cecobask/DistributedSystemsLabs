package db;

import models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Tsvetoslav Dimov
 * Student ID: 20077038
 * Module: Distributed Systems
 */
public class StudentDaoImplementation {

    // Establish database connection.
    private final Connection conn = DatabaseConnection.createConnection();

    /**
     * Add a new Student object to the database.
     * @param student Student object
     * @throws SQLException Ensures SQLException will be handled when calling this method.
     */
    public void addStudent(Student student) throws SQLException {
        String SQL_ADD_STUDENT = "INSERT INTO students (SID, STUD_ID, FNAME, SNAME) VALUES (?, ?, ? ,?)"; // SQL query.
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_ADD_STUDENT);
        setPrepStatParams(preparedStatement, student);
        preparedStatement.executeUpdate();
    }

    /**
     * Retrieve a Student object record from the database based on their student ID.
     * @param studID Student ID
     * @return Student
     */
    public Student getStudentByStudID(int studID) {
        Student student = new Student(); // Empty Student object.
        String SQL_GET_STUDENT_BY_STUD_ID = "SELECT * FROM students WHERE STUD_ID=?"; // SQL query.
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_STUDENT_BY_STUD_ID)) {
            preparedStatement.setInt(1, studID); // Set STUD_ID.
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) { // Loop through every Student object.
                constructStudentObject(student, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    /**
     * Retrieve an ArrayList of all Student objects from the database.
     * @return ArrayList<Student>
     */
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> allStudents = new ArrayList<>(); // Empty ArrayList.
        String SQL_GET_ALL_STUDENTS = "SELECT * FROM students"; // SQL query.
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ALL_STUDENTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) { // Loop through every Student object.
                Student student = new Student();
                constructStudentObject(student, rs);
                allStudents.add(student); // Add to the ArrayList.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allStudents;
    }

    /**
     * Update a Student object's fields with the fields of the passed in Student object.
     * @param student Student
     * @return boolean Whether the update was successful.
     */
    public boolean updateStudent(Student student) {
        boolean result = false; // Set the default to false.
        String SQL_UPDATE_STUDENT = "UPDATE students SET SID=?, STUD_ID=?, FNAME=?, SNAME=? WHERE STUD_ID=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE_STUDENT)) {
            setPrepStatParams(preparedStatement, student);
            preparedStatement.setInt(5, student.getStudID()); // Set STUD_ID to search for.
            result = preparedStatement.executeUpdate() == 1; // boolean value indicating whether the update was successful.
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Deletes a Student record from the database based on their student ID.
     * @param studID Student ID
     * @return boolean Whether the update was successful.
     */
    public boolean deleteStudent(int studID) {
        boolean result = false; // Set the default to false.
        String SQL_DELETE_STUDENT = "DELETE FROM students WHERE STUD_ID=?"; // SQL query.
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE_STUDENT)) {
            preparedStatement.setInt(1, studID); // Set the STUD_ID.
            result = preparedStatement.executeUpdate() == 1; // boolean value indicating whether the update was successful.
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Method that reduces boilerplate code by setting PreparedStatement's parameters to Student object's fields.
     * @param preparedStatement PreparedStatement
     * @param student Student
     * @throws SQLException Ensures SQLException will be handled when calling this method.
     */
    private void setPrepStatParams(PreparedStatement preparedStatement, Student student) throws SQLException {
        preparedStatement.setInt(1, student.getSID());
        preparedStatement.setInt(2, student.getStudID());
        preparedStatement.setString(3, student.getFirstName());
        preparedStatement.setString(4, student.getLastName());
    }

    /**
     * Method that reduces boilerplate code by setting Student object's fields to field values from ResultSet object.
     * @param student Student
     * @param rs ResultSet
     * @throws SQLException Ensures SQLException will be handled when calling this method.
     */
    private void constructStudentObject(Student student, ResultSet rs) throws SQLException {
        student.setSID(rs.getInt("SID"));
        student.setStudID(rs.getInt("STUD_ID"));
        student.setFirstName(rs.getString("FNAME"));
        student.setLastName(rs.getString("SNAME"));
    }
}
