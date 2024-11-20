package EmployeeManagement;

import Exercise.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        Scanner scan = new Scanner(System.in);

        Employee[] empList = new Employee[100];
        int index = 0;
        boolean flag = true;

        while (flag){
            Employee employee = Employee.getEmployeeInfo();

                if(index < 100){
                    empList[index++] = employee;
                }else{
                    System.out.println("Exceeded Employee!!!!!!");

                }
            System.out.print("Wanna add more?? Yes or No : ");
            String ans = scan.nextLine();
            if (ans.equalsIgnoreCase("no")) {
                flag = false; // Exit loop
            }
        }
        displayEmployees(index,empList);
        scan.close();
    }

    public static void displayEmployees(int index,Employee[] empList){
        double getSalary = 0;
        double avgSalary = 0;
        for(int i = 0; i < index; i++){
            Employee employee = empList[i];

            getSalary += employee.getSalary();
             avgSalary = getSalary / index;

            System.out.println(employee);

        }
        System.out.println("Average Salary is : "+avgSalary);
    }
}
