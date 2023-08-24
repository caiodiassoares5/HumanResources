package application;

import java.sql.Connection;
import java.util.Scanner;

import model.entities.Department;
import model.entities.Employee;
import repositories.DatabaseConnection;
import repositories.DepartmentRepository;
import repositories.EmployeeRepository;

public class App {
    public static void main(String[] args) throws Exception {

        Connection connection = DatabaseConnection.getConnection();
        DepartmentRepository departmentRepository = new DepartmentRepository(connection);
        EmployeeRepository employeeRepository = new EmployeeRepository(connection);

        Scanner scanner = new Scanner(System.in);
        Integer menuOption;

        System.out.println("\n*******************HR APPLICATION*******************");

        System.out.println("Please enter one option: " 
        + "\n1: For a complete list of employees."
        + "\n2: To find a specific employee."
        + "\n3: To create a new employee."
        + "\n4: to create a new department."
        + "\n5: For a complete lsit of departments."
        + "\n6: To find a specific department.");

        menuOption = scanner.nextInt();
        scanner.nextLine();

        switch (menuOption) {
            case 1:
                employeeRepository.findAll();
                break;
            case 2:
                getAndfindEmployeeById(employeeRepository);
                break;
            case 3:
                newEmployee(departmentRepository, employeeRepository);
                break;
            case 4:
                newDepartment(departmentRepository);
                break;
            case 5:
                departmentRepository.findAll();
                break;
            case 6:
                getAndfindDepartmentById(departmentRepository);
                break;
            default:
                break;
        }
        connection.close();
        scanner.close();
    }


    public static void getAndfindEmployeeById (EmployeeRepository employeeRepository) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the employeeId: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();
        employeeRepository.findById(employeeId);
        scanner.close();
    }




    public static void newEmployee(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter new employee data: ");        
        System.out.print("Name :");
        String employeeNameString = scanner.nextLine();
        System.out.print("Salary: ");
        double salaryDouble = scanner.nextDouble();
        System.out.println("DepartmentId: ");
        int departmendId = scanner.nextInt();
        scanner.nextLine();

        Department department = departmentRepository.findById(departmendId);     
        Employee employee = new Employee(employeeNameString, salaryDouble, department);

        employeeRepository.save(employee);
        scanner.close();
    }


    public static void newDepartment(DepartmentRepository departmentRepository) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter department data: ");
        System.out.println("Name: ");
        String departmentName = scanner.nextLine();
        Department department = new Department(departmentName);
        departmentRepository.save(department);    
        scanner.close();
    }


    public static void getAndfindDepartmentById(DepartmentRepository departmentRepository) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the departmentId: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Department department = departmentRepository.findById(id);
        System.out.println(department);
        scanner.close();
    }

}
