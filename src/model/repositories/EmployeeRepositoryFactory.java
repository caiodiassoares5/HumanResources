package model.repositories;


import model.repositories.impl.DatabaseConnection;
import model.repositories.impl.EmployeeRepository;

public class EmployeeRepositoryFactory {
    
    public static EmployeeRepositoryInterface createEmployeeRepository() {
        return new EmployeeRepository(DatabaseConnection.getConnection());
    }

}
