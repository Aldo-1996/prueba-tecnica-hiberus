package com.aldo.hiberus.prueba_tecnica.services;

import java.util.List;

import com.aldo.hiberus.prueba_tecnica.entities.dto.DeparmentDTO;

public interface DeparmentService {

    List<DeparmentDTO> findAll();

    void save(DeparmentDTO deparmentDTO);
    
    void delete(Long id);

}
