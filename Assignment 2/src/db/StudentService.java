package db;

import models.Student;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Tsvetoslav Dimov
 * Student ID: 20077038
 * Module: Distributed Systems
 */
public class StudentService {

    // StudentDaoImplementation class instance.
    private final StudentDaoImplementation studentDaoImplementation = new StudentDaoImplementation();

    /**
     * Add a new Student object to the database.
     * @param student Student object
     * @throws SQLException Ensures SQLException will be handled when calling this method.
     */
    public void addStudent(Student student) throws SQLException {
        studentDaoImplementation.addStudent(student);
    }

    /**
     * Retrieve a Student object record from the database based on their student ID.
     * @param studID Student ID
     * @return Student
     */
    public Student getStudentByStudID(int studID) {
        return studentDaoImplementation.getStudentByStudID(studID);
    }

    /**
     * Retrieve an ArrayList of all Student objects from the database.
     * @return ArrayList<Student>
     */
    public ArrayList<Student> getAllStudents() {
        return studentDaoImplementation.getAllStudents();
    }

    /**
     * Update a Student object's fields with the fields of the passed in Student object.
     * @param student Student
     * @return boolean Whether the update was successful.
     */
    public boolean updateStudent(Student student) {
        return studentDaoImplementation.updateStudent(student);
    }

    /**
     * Deletes a Student record from the database based on their student ID.
     * @param studID Student ID
     * @return boolean Whether the update was successful.
     */
    public boolean deleteStudent(int studID) {
        return studentDaoImplementation.deleteStudent(studID);
    }
}
