package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Department;
import model.exceptions.CustomException;


public class DepartmentRepository {

    //private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Connection connection = null;
    List<Department> departmentList = new ArrayList<>();

    public DepartmentRepository(Connection connection){
        this.connection = connection;
        }

    public void findAll() {
        StringBuilder statementText = new StringBuilder("SELECT * FROM departments;");        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(statementText.toString());
            fetchResultSet();
            departmentList.stream().forEach(System.out::println);
        } catch (SQLException e) {
            throw new CustomException("FindAll Department exception: " + e.getMessage());
        } finally {
            
        }
    }


    public void fetchResultSet() {        
        try {
            while (resultSet.next()) {
                Department department = new Department(resultSet.getInt("id"), resultSet.getString("name"));
                departmentList.add(department);
            }
        } catch (SQLException e) {
            throw new CustomException("Department fetch result set exception: " + e.getMessage());
        }
    }


    public Department findById(int id) {
        StringBuilder preparedStatementText = new StringBuilder("SELECT * FROM departments ");
        preparedStatementText.append("WHERE id = ?");
        try {
            preparedStatement = connection.prepareStatement(preparedStatementText.toString());
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            fetchResultSet();
            return departmentList.get(0);            
        } catch (SQLException e) {
            throw new CustomException("Find department by id exception: " + e.getMessage());
        }
    }


    public void save(Department department){
        StringBuilder preparedStatementText = new StringBuilder("INSERT INTO departments ");
        preparedStatementText.append("(name) values (?)");
        try {
            preparedStatement = connection.prepareStatement(preparedStatementText.toString());
            preparedStatement.setString(1, department.getNameString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Save department exception: " + e.getMessage());
        } finally {
            tideUp();
        }
    }


    public void tideUp() {
        try {
            if (statement !=null) {
                statement.close();
            } 
            if (preparedStatement !=null) {
                preparedStatement.close();
            }
            if (resultSet !=null) {
                resultSet.close();
            }
        } catch(SQLException e) {
            throw new CustomException("Department tide up exception: "+ e.getMessage());
        }
    }


} 



