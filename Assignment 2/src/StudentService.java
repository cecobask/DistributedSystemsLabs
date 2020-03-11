import java.sql.SQLException;
import java.util.ArrayList;

public class StudentService {

    private final StudentDaoImplementation studentDaoImplementation = new StudentDaoImplementation();

    public void addStudent(Student student) throws SQLException {
        studentDaoImplementation.addStudent(student);
    }

    public Student getStudentBySID(String sid) {
        return studentDaoImplementation.getStudentBySID(sid);
    }

    public ArrayList<Student> getAllStudents() {
        return studentDaoImplementation.getAllStudents();
    }

    public boolean updateStudent(Student student) {
        return studentDaoImplementation.updateStudent(student);
    }

    public boolean deleteStudent(String sid) {
        return studentDaoImplementation.deleteStudent(sid);
    }
}
