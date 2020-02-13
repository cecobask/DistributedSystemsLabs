import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public interface EmployeeDao {

    public void addEmployee(Employee employee) throws SQLException;

    public Employee getEmployeeBySSN(String ssn);

    public ArrayList<Employee> getAllEmployees();

    public boolean updateEmployee(Employee employee);

    public boolean deleteEmployee(String ssn);

}
