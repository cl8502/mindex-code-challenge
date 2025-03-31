package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService{
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Creates a new compensation entry for an employee.
     *
     * @param compensation The compensation details to be saved.
     * @return The created compensation object.
     * @throws RuntimeException if the employee ID is invalid.
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);
        String id = compensation.getEmployee().getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        compensation.setEmployee(employee);
        compensationRepository.insert(compensation);
    
        return compensation;
    }

    /**
     * Retrieves the compensation details for a given employee ID.
     *
     * @param id The ID of the employee whose compensation record is requested.
     * @return The compensation details associated with the employee.
     * @throws RuntimeException if the employee ID is invalid.
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Reading compensation with id [{}]", id);
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        Compensation compensation = compensationRepository.findByEmployee(employee);
        return compensation;
    }
}
