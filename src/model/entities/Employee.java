package model.entities;

public class Employee {
    
    private Integer idInteger;
    private String nameString;
    private Double salaryDouble;
    private Department department;
    
    public Employee() {
    }

    public Employee(Integer idInteger, String nameString, Double salaryDouble, Department department) {
        this.idInteger = idInteger;
        this.nameString = nameString;
        this.salaryDouble = salaryDouble;
        this.department = department;
    }

    public Integer getIdInteger() {
        return idInteger;
    }

    public Employee setIdInteger(Integer idInteger) {
        this.idInteger = idInteger;
        return this;
    }

    public String getNameString() {
        return nameString;
    }

    public Employee setNameString(String nameString) {
        this.nameString = nameString;
        return this;
    }

    public Double getSalaryDouble() {
        return salaryDouble;
    }

    public Employee setSalaryDouble(Double salaryDouble) {
        this.salaryDouble = salaryDouble;
        return this;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee [idInteger=" + idInteger + ", nameString=" + nameString + ", salaryDouble=" + salaryDouble
                + ", department=" + department + "]\n";
    }


    

    


}
