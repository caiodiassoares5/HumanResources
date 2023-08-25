package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.entities.Department;
import model.entities.Employee;
import model.repositories.DepartmentRepositoryFactory;
import model.repositories.DepartmentRepositoryInterface;
import model.repositories.EmployeeRepositoryFactory;
import model.repositories.EmployeeRepositoryInterface;


public class App {
    public static void main(String[] args) throws Exception {
        
        DepartmentRepositoryInterface departmentRepositoryInterface = DepartmentRepositoryFactory.createDepartmentRepository();
        EmployeeRepositoryInterface employeeRepositoryInterface = EmployeeRepositoryFactory.createEmployeeRepository();
 
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
                getAllEmployees(employeeRepositoryInterface);
                break;
            case 2:
                getAndfindEmployeeById(employeeRepositoryInterface);
                break;
            case 3:
                newEmployee(departmentRepositoryInterface, employeeRepositoryInterface);
                break;
            case 4:
                newDepartment(departmentRepositoryInterface);
                break;
            case 5:
                getAllDepartments(departmentRepositoryInterface);
                break;
            case 6:
                getAndfindDepartmentById(departmentRepositoryInterface);
                break;
            default:
                break;
        }        
        scanner.close();
    }


    public static void getAllEmployees(EmployeeRepositoryInterface employeeRepositoryInterface){
        List<Employee> employeeList = new ArrayList<>();
        employeeList = employeeRepositoryInterface.findAll();
        employeeList.stream().forEach(System.out::println);
    }   

    public static void getAllDepartments(DepartmentRepositoryInterface departmentRepositoryInterface) {
        List<Department> departmentList = new ArrayList<>();
        departmentList = departmentRepositoryInterface.findAll();
        departmentList.stream().forEach(System.out::println);
    }

    public static void getAndfindEmployeeById (EmployeeRepositoryInterface employeeRepositoryInterface) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the employeeId: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();
        Employee employee = employeeRepositoryInterface.findById(employeeId);
        System.out.println(employee);
        scanner.close();
    }

    public static void newEmployee(DepartmentRepositoryInterface departmentRepositoryInterface, EmployeeRepositoryInterface employeeRepositoryInterface) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter new employee data: ");        
        System.out.print("Name :");
        String employeeNameString = scanner.nextLine();
        System.out.print("Salary: ");
        double salaryDouble = scanner.nextDouble();
        System.out.println("DepartmentId: ");
        int departmendId = scanner.nextInt();
        scanner.nextLine();

        Department department = departmentRepositoryInterface.findById(departmendId);     
        Employee employee = new Employee(employeeNameString, salaryDouble, department);

        employeeRepositoryInterface.save(employee);
        scanner.close();
    }


    public static void newDepartment(DepartmentRepositoryInterface departmentRepositoryInterface) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter department data: ");
        System.out.println("Name: ");
        String departmentName = scanner.nextLine();
        Department department = new Department(departmentName);
        departmentRepositoryInterface.save(department);    
        scanner.close();
    }


    public static void getAndfindDepartmentById(DepartmentRepositoryInterface departmentRepositoryInterface) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the departmentId: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Department department = departmentRepositoryInterface.findById(id);
        System.out.println(department);
        scanner.close();
    }

}
