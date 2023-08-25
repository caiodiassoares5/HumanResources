package model.repositories;

import java.sql.Connection;

import repositories.DepartmentRepository;

public class DepartmentRepositoryFactory {
    
    public static DepartmentRepositoryInterface createDepartmentRepository(Connection connection) {
        return new DepartmentRepository(connection);
    }

}
