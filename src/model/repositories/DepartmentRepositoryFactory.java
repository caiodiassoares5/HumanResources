package model.repositories;

import model.repositories.impl.DatabaseConnection;
import model.repositories.impl.DepartmentRepository;

public class DepartmentRepositoryFactory {
    
    public static DepartmentRepositoryInterface createDepartmentRepository() {
        return new DepartmentRepository(DatabaseConnection.getConnection());
    }

}
