import java.sql.*;
import java.util.ArrayList;

public class EmployeeDaoImplementation implements EmployeeDao {
    private final Connection conn = DatabaseConnection.createConnection();

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        String SQL_ADD_EMPLOYEE = "INSERT INTO employees (ssn, dob, name, address, salary, gender) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_ADD_EMPLOYEE);
        preparedStatement.setString(1, employee.getSsn());
        preparedStatement.setDate(2, employee.getDob());
        preparedStatement.setString(3, employee.getName());
        preparedStatement.setString(4, employee.getAddress());
        preparedStatement.setInt(5, employee.getSalary());
        preparedStatement.setString(6, employee.getGender());
        preparedStatement.executeUpdate();
    }

    @Override
    public Employee getEmployeeBySSN(String ssn) {
        Employee employee = new Employee();
        String SQL_GET_EMPLOYEE_BY_SSN = "SELECT * FROM employees WHERE ssn=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_EMPLOYEE_BY_SSN)) {
            preparedStatement.setString(1, ssn);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                employee.setSsn(rs.getString("ssn"));
                employee.setDob(rs.getDate("dob"));
                employee.setName(rs.getString("name"));
                employee.setAddress(rs.getString("address"));
                employee.setSalary(rs.getInt("salary"));
                employee.setGender(rs.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> allEmployees = new ArrayList<>();
        String SQL_GET_ALL_EMPLOYEES = "SELECT * FROM employees";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ALL_EMPLOYEES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setSsn(rs.getString("ssn"));
                employee.setDob(rs.getDate("dob"));
                employee.setName(rs.getString("name"));
                employee.setAddress(rs.getString("address"));
                employee.setSalary(rs.getInt("salary"));
                employee.setGender(rs.getString("gender"));
                allEmployees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allEmployees;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        boolean result = false;
        String SQL_UPDATE_EMPLOYEE = "UPDATE employees SET ssn=?, dob=?, name=?, address=?, salary=?, gender=? WHERE ssn=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getSsn());
            preparedStatement.setDate(2, (Date) employee.getDob());
            preparedStatement.setString(3, employee.getName());
            preparedStatement.setString(4, employee.getAddress());
            preparedStatement.setInt(5, employee.getSalary());
            preparedStatement.setString(6, employee.getGender());
            preparedStatement.setString(7, employee.getSsn());

            result = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean deleteEmployee(String ssn) {
        boolean result = false;
        String SQL_DELETE_EMPLOYEE = "DELETE FROM employee WHERE ssn=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE_EMPLOYEE)) {
            preparedStatement.setString(1, ssn);
            result = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
