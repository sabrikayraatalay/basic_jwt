package com.KayraAtalay.controller;

import com.KayraAtalay.dto.DtoEmployee;

public interface IEmployeeController {

	public DtoEmployee findEmployeeById(Long id);
}
