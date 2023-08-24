package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Department;
import model.entities.Employee;
import model.exceptions.CustomException;

public class EmployeeRepository {
    
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    List<Employee> resultList = new ArrayList<>(); 

    public EmployeeRepository() {
        try{
            connection = DatabaseConnection.getConnection();
        } catch(RuntimeException e) {
            throw new CustomException("Department repository initialization exception. " + e.getMessage());
        }
    }


    public void findAll() {
        try {
            statement = connection.createStatement();
            StringBuilder statementText = new StringBuilder("SELECT ");
            statementText.append("e.*, d.name as departmentname " );
            statementText.append("FROM employees e INNER JOIN departments d ON ");
            statementText.append("e.departmentid = d.id;");
            resultSet = statement.executeQuery(statementText.toString());
            fetchResultSet();            
            resultList.stream().forEach(System.out::println);
        } catch (SQLException e) {
            throw new CustomException("Find all employee exception: " + e.getMessage());
        } finally {
            tideUp();
        }
    }


    private void fetchResultSet() {        
        try {
            while (resultSet.next()) {
                Department department = new Department(
                        resultSet.getInt("departmentid"), 
                        resultSet.getString("departmentname"));
                Employee employee = new Employee(
                        resultSet.getInt("id"), 
                        resultSet.getString("name"),
                        resultSet.getDouble("salary"),
                        department);            
                resultList.add(employee);
            }
        } catch (SQLException e) {
            throw new CustomException("Fetch all results exception: " + e.getMessage());
        }
    }


    private void tideUp() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        catch (SQLException e) {
            throw new CustomException("Tide up error: " + e.getMessage());
        }
    }



    


}
