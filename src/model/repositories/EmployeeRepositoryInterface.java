package model.repositories;

import java.util.List;

import model.entities.Employee;

public interface EmployeeRepositoryInterface {
    
    public List<Employee> findAll();
    public Employee findById(int id);
    public void save(Employee employee);
    public void tideUp();
}
