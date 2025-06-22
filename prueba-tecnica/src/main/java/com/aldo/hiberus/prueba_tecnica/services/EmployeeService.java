package com.aldo.hiberus.prueba_tecnica.services;

import java.util.List;
import java.util.Optional;

import com.aldo.hiberus.prueba_tecnica.entities.dto.EmployeeDTO;

public interface EmployeeService {

    List<EmployeeDTO> findAll();

    void createEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);

    Optional<EmployeeDTO> highestSalaryEmployee(String enabled);

    Optional<EmployeeDTO> lowerAgeEmployee(String enabled);

    Integer countLastMonthEmployee(String enabled);

}
