import java.sql.SQLException;
import java.util.ArrayList;

public class StudentService {

    private final StudentDaoImplementation studentDaoImplementation = new StudentDaoImplementation();

    public void addStudent(Student student) throws SQLException {
        studentDaoImplementation.addStudent(student);
    }

    public Student getStudentByStudID(int studID) {
        return studentDaoImplementation.getStudentByStudID(studID);
    }

    public ArrayList<Student> getAllStudents() {
        return studentDaoImplementation.getAllStudents();
    }

    public boolean updateStudent(Student student) {
        return studentDaoImplementation.updateStudent(student);
    }

    public boolean deleteStudent(int studID) {
        return studentDaoImplementation.deleteStudent(studID);
    }
}
