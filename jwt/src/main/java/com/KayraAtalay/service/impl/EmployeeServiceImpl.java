package com.KayraAtalay.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KayraAtalay.dto.DtoDepartment;
import com.KayraAtalay.dto.DtoEmployee;
import com.KayraAtalay.entities.Department;
import com.KayraAtalay.entities.Employee;
import com.KayraAtalay.repository.EmployeeRepository;
import com.KayraAtalay.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public DtoEmployee findEmployeeById(Long id) {
		DtoEmployee dtoEmployee = new DtoEmployee();
		DtoDepartment dtoDepartment = new DtoDepartment();
		
		Optional<Employee> optional = employeeRepository.findById(id);
		
		if(optional.isEmpty()) {
			return null;
		}
		
		Employee employee = optional.get();
		Department department = employee.getDepartment();
		BeanUtils.copyProperties(employee, dtoEmployee);
		BeanUtils.copyProperties(department, dtoDepartment);
		dtoEmployee.setDepartment(dtoDepartment);
		
		return dtoEmployee;
		
		
	}

}
