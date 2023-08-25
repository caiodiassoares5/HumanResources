package model.repositories;

import model.entities.Department;

public interface DepartmentRepositoryInterface {
    
    public void findAll();
    public void fetchResultSet();
    public Department findById(int id);
    public void save(Department department);
    public void tideUp();
}


