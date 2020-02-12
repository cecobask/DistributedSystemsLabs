import java.sql.Date;

public class Employee {

    private String ssn;
    private Date dob;
    private String name;
    private String address;
    private int salary;
    private String gender;

    public Employee(String ssn, Date dob, String name, String address, int salary, String gender) {
        this.ssn = ssn;
        this.dob = dob;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.gender = gender;
    }

    public Employee(){}

    @Override
    public String toString() {
        return "Employee{" +
                "ssn='" + ssn + '\'' +
                ", dob='" + dob + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
