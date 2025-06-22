package com.aldo.hiberus.prueba_tecnica.mapper;

import org.mapstruct.Mapper;


import com.aldo.hiberus.prueba_tecnica.entities.Department;
import com.aldo.hiberus.prueba_tecnica.entities.dto.DeparmentDTO;

@Mapper(componentModel = "spring")
public interface DeparmentMapper {

    //DeparmentMapper mapper = Mappers.getMapper(DeparmentMapper.class);

    DeparmentDTO toDeparmentDTO(Department department);

    Department toDepartment(DeparmentDTO deparmentDTO);

}
