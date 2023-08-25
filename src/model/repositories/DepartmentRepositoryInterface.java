package model.repositories;

import java.util.List;

import model.entities.Department;

public interface DepartmentRepositoryInterface {
    
    public List<Department> findAll();
    public void fetchResultSet();
    public Department findById(int id);
    public void save(Department department);
    public void tideUp();
}


