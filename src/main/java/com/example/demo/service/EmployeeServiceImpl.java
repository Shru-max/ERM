package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> getAllEmployee() {
        // TODO Auto-generated method stub
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(int theId)
    {
        Employee theEmployee = null;
        Optional<Employee> result=employeeRepository.findById(theId);
        if(result.isPresent())
        {
            theEmployee=result.get();
        }
        else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public void deletebyid(int theId) {
        Employee theEmployee = null;
        Optional<Employee> result=employeeRepository.findById(theId);
        if(result.isPresent())
        {
          employeeRepository.deleteById(theId);

        }
        else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }

    }

}