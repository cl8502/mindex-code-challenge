package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Retrieves the reporting structure for a given employee.
     *
     * @param id The ID of the employee whose reporting structure is requested.
     * @return The reporting structure containing the employee and the number of direct reports.
     * @throws RuntimeException if the employee ID is invalid.
     */
    @GetMapping("/report/{id}")
    public ReportingStructure getReportingStructure(@PathVariable String id){
        LOG.debug("Retrieving number of direct reports for employee [{}]", id);
        ReportingStructure report = new ReportingStructure();
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        report.setEmployee(employee);
        report.set_numberOfReports(employee.count_numberOfReports());
        return report;
    }
}
