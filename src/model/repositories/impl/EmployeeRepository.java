package model.repositories.impl;

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
import model.repositories.EmployeeRepositoryInterface;

public class EmployeeRepository implements EmployeeRepositoryInterface {
    
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    List<Employee> resultList = new ArrayList<>(); 

    public EmployeeRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Employee findById(int id) {
        StringBuilder preparedStatementText = new StringBuilder("SELECT ");
        preparedStatementText.append("e.*, d.name as departmentname ");
        preparedStatementText.append("FROM employees e INNER JOIN departments d ON ");
        preparedStatementText.append("e.departmentid = ?");       
        try {
            preparedStatement = connection.prepareStatement(preparedStatementText.toString());
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            fetchResultSet();
            return resultList.get(0);
        } catch (SQLException e) {
           throw new CustomException("Find by Id runtime exception: " + e.getMessage());
        } finally {
            tideUp();
        }
    }


    public List<Employee> findAll() {
        try {
            statement = connection.createStatement();
            StringBuilder statementText = new StringBuilder("SELECT ");
            statementText.append("e.*, d.name as departmentname " );
            statementText.append("FROM employees e INNER JOIN departments d ON ");
            statementText.append("e.departmentid = d.id;");
            resultSet = statement.executeQuery(statementText.toString());
            fetchResultSet();            
            return resultList;
            //resultList.stream().forEach(System.out::println);
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
            throw new CustomException("Fetch resultSet exception: " + e.getMessage());
        }
    }


    public void tideUp() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet !=null) {
                resultSet.close();
            }            
            if (connection !=null) {
                DatabaseConnection.closeConnection();
            }
        }
        catch (SQLException e) {
            throw new CustomException("Tide up error: " + e.getMessage());
        }
    }


    public void save(Employee employee) {
        StringBuilder preparedStatementText = new StringBuilder("INSERT INTO employees ");
        preparedStatementText.append("(name, salary, departmentid) ");
        preparedStatementText.append("VALUES (?,?,?);");
        try {
            preparedStatement = connection.prepareStatement(preparedStatementText.toString());
            preparedStatement.setString(1, employee.getNameString());
            preparedStatement.setDouble(2, employee.getSalaryDouble());
            preparedStatement.setInt(3, employee.getDepartment().getIdInteger());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Employee save exception: " + e.getMessage());
        } finally {
            tideUp();
        }

    }

    


}
