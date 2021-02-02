package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        System.out.println("before: " + employeeDTO);
        Employee retrievedEmployee = employeeRepository.save(employee);
        EmployeeDTO retrievedEmployeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(retrievedEmployee, retrievedEmployeeDTO);
        System.out.println("after: " + retrievedEmployeeDTO);
        return retrievedEmployeeDTO;
    }

    public EmployeeDTO findById(long id) {
        Employee retrievedEmployee = employeeRepository.getOne(id);
        EmployeeDTO retrievedEmployeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(retrievedEmployee, retrievedEmployeeDTO);
        return retrievedEmployeeDTO;
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }
}
