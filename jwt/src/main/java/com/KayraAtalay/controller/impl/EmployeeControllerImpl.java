package com.KayraAtalay.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KayraAtalay.controller.IEmployeeController;
import com.KayraAtalay.dto.DtoEmployee;
import com.KayraAtalay.service.IEmployeeService;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeControllerImpl implements IEmployeeController {
	
	@Autowired
	private IEmployeeService employeeService;

	@GetMapping(path = "/{id}")
	@Override
	public DtoEmployee findEmployeeById(@PathVariable(value = "id") Long id) {
		return  employeeService.findEmployeeById(id);
	}

}
