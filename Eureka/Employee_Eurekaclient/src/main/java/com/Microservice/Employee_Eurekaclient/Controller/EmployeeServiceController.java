package com.Microservice.Employee_Eurekaclient.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Microservice.Employee_Eurekaclient.Model.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeServiceController {

    private static Map<String, List<Employee>> orgDB = new HashMap<String, List<Employee>>();

    static {
        orgDB = new HashMap<String, List<Employee>>();

        List<Employee> employeeList = new ArrayList<Employee>();
        Employee std = new Employee("Dev", "IT");
        employeeList.add(std);
        std = new Employee("Raj", "Admin");
        employeeList.add(std);

        orgDB.put("City", employeeList);

        employeeList = new ArrayList<Employee>();
        std = new Employee("Rao", "Cloud");
        employeeList.add(std);
        std = new Employee("Kumar", "Data");
        employeeList.add(std);

        orgDB.put("chase", employeeList);

    }

    @RequestMapping(value = "/getEmployeeDetailsForOrgnization/{orgname}", method = RequestMethod.GET)
    public List<Employee> getStudents(@PathVariable String orgname) {
        System.out.println("Getting Student details for " + orgname);

        List<Employee> studentList = orgDB.get(orgname);
        if (studentList == null) {
            studentList = new ArrayList<Employee>();
            Employee std = new Employee("Not Found", "N/A");
            studentList.add(std);
        }
        return studentList;
    }
}