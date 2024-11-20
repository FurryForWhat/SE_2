package Exercise;

import java.util.Scanner;

public class Employee {
    private String name;
    private String email;
    private String ph_num;
    private String dprt ;
    private double salary;

    public Employee(){

    }
    public Employee(String name, String email, String ph_num, String dprt, double salary){
        this.name = name;
        this.email = email;
        this.ph_num = ph_num;
        this.dprt = dprt;
        this.salary = salary;
    }
    public Double getSalary(){
        return this.salary;
    }
    public static Employee getEmployeeInfo(){
     Scanner scan = new Scanner(System.in);

        System.out.print("Enter Name -> ");
        String name = scan.nextLine();

        System.out.print("Enter Email -> ");
        String email = scan.nextLine();

        System.out.print("Enter Phone Number -> ");
        String phNum = scan.nextLine();

        System.out.print("Enter department -> ");
        String dprt = scan.nextLine();

        System.out.print("Enter salary -> ");
        double salary  = scan.nextDouble();

        Employee employee = new Employee();
        employee.name = name;
        employee.email = email;
        employee.ph_num = phNum;
        employee.salary = salary;
        employee.dprt = dprt;
//        System.out.println("Employee Name : "+employee.name);
//        System.out.println("Employee email : "+employee.email);
//        System.out.println("Employee Phone Number : "+employee.ph_num);
//        System.out.println("Employee Salary : "+employee.salary);
//        System.out.println("Employee Department : "+employee.dprt);
        return employee;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", ph_num='" + ph_num + '\'' +
                ", dprt='" + dprt + '\'' +
                ", salary=" + salary +
                '}';
    }
}
