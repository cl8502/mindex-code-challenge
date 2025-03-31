package com.mindex.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;


@Component
public class DataBootstrap {
    private static final String DATASTORE_LOCATION = "/static/employee_database.json";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired CompensationRepository compensationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        InputStream inputStream = this.getClass().getResourceAsStream(DATASTORE_LOCATION);


        Employee[] employees = null;

        try {
            employees = objectMapper.readValue(inputStream, Employee[].class);
            System.out.println(employees);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Employee employee : employees) {
            employeeRepository.insert(employee);
        }

        //testcase
        String id = "03aa1462-ffa9-4978-901b-7c001562cf6f";
        Employee person = employeeRepository.findByEmployeeId(id);
        System.out.println(person.getFirstName());
        Compensation compensation = new Compensation();
        compensation.setEmployee(person);
        compensation.setSalary(200000);
        compensation.setEffectiveDate(LocalDate.now());
        compensationRepository.insert(compensation);
        Compensation found = compensationRepository.findByEmployee(person);
        System.out.println(found.getSalary());
        System.out.println(found.getEffectiveDate());
        System.out.println(found.getEmployee().getEmployeeId());
        System.out.println(found.getEmployee().count_numberOfReports());
    }
}
