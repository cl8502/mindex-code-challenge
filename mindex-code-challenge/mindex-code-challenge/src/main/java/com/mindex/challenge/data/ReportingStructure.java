package com.mindex.challenge.data;

public class ReportingStructure{
    private Employee employee; 
    private int numberOfReports;

    public ReportingStructure(){
    }

    public Employee getEmployee(){
        return employee;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public int get_numberOfReports(){
        return numberOfReports;
    }

    public void set_numberOfReports(int numberOfReports){
        this.numberOfReports = numberOfReports;
    }
}