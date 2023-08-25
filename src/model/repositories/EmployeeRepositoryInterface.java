package model.repositories;

import model.entities.Employee;

public interface EmployeeRepositoryInterface {
    
    public void findAll();
    public Employee findById(int id);
    public void save(Employee employee);
    public void tideUp();
}
