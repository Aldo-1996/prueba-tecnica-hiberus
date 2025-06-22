package com.aldo.hiberus.prueba_tecnica.mapper;

import org.mapstruct.Mapper;

import com.aldo.hiberus.prueba_tecnica.entities.Employee;
import com.aldo.hiberus.prueba_tecnica.entities.dto.EmployeeDTO;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO toEmployeeDTO(Employee employee);

    Employee toEmployee(EmployeeDTO employeeDTO);
    
}
