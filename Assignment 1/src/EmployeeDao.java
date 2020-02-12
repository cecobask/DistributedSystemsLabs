import java.util.ArrayList;

public interface EmployeeDao {

    public boolean addEmployee(Employee employee);

    public Employee getEmployeeBySSN(String ssn);

    public ArrayList<Employee> getAllEmployees();

    public boolean updateEmployee(Employee employee);

    public boolean deleteEmployee(String ssn);

}
