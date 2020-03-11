package db;

import db.DatabaseConnection;
import models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDaoImplementation {

    private final Connection conn = DatabaseConnection.createConnection();

    public void addStudent(Student student) throws SQLException {
        String SQL_ADD_STUDENT = "INSERT INTO students (SID, STUD_ID, FNAME, SNAME) VALUES (?, ?, ? ,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_ADD_STUDENT);
        setPrepStatParams(preparedStatement, student);
        preparedStatement.executeUpdate();
    }

    public Student getStudentByStudID(int studID) {
        Student student = new Student();
        String SQL_GET_STUDENT_BY_STUD_ID = "SELECT * FROM students WHERE STUD_ID=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_STUDENT_BY_STUD_ID)) {
            preparedStatement.setInt(1, studID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                constructStudentObject(student, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> allStudents = new ArrayList<>();
        String SQL_GET_ALL_STUDENTS = "SELECT * FROM students";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ALL_STUDENTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                constructStudentObject(student, rs);
                allStudents.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allStudents;
    }

    public boolean updateStudent(Student student) {
        boolean result = false;
        String SQL_UPDATE_STUDENT = "UPDATE students SET SID=?, STUD_ID=?, FNAME=?, SNAME=? WHERE STUD_ID=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE_STUDENT)) {
            setPrepStatParams(preparedStatement, student);
            preparedStatement.setInt(5, student.getStudID());
            result = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean deleteStudent(int studID) {
        boolean result = false;
        String SQL_DELETE_STUDENT = "DELETE FROM students WHERE STUD_ID=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE_STUDENT)) {
            preparedStatement.setInt(1, studID);
            result = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void setPrepStatParams(PreparedStatement preparedStatement, Student student) throws SQLException {
        preparedStatement.setInt(1, student.getSID());
        preparedStatement.setInt(2, student.getStudID());
        preparedStatement.setString(3, student.getFirstName());
        preparedStatement.setString(4, student.getLastName());
    }

    private void constructStudentObject(Student student, ResultSet rs) throws SQLException {
        student.setSID(rs.getInt("SID"));
        student.setStudID(rs.getInt("STUD_ID"));
        student.setFirstName(rs.getString("FNAME"));
        student.setLastName(rs.getString("SNAME"));
    }
}
