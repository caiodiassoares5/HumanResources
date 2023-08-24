package application;

import repositories.EmployeeRepository;

public class App {
    public static void main(String[] args) throws Exception {

        EmployeeRepository employeeRepository = new EmployeeRepository();        
        System.out.println("Fetching all employees....");
        employeeRepository.findAll();
        System.out.println("Done");
    }
}
