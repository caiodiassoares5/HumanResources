package model.repositories;

import java.sql.Connection;

import repositories.EmployeeRepository;

public class EmployeeRepositoryFactory {
    
    public static EmployeeRepositoryInterface createEmployeeRepository(Connection connection) {
        return new EmployeeRepository(connection);
    }

}
