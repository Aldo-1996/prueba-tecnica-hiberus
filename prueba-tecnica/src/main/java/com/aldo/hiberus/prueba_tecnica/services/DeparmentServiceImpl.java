package com.aldo.hiberus.prueba_tecnica.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aldo.hiberus.prueba_tecnica.entities.Department;
import com.aldo.hiberus.prueba_tecnica.entities.dto.DeparmentDTO;
import com.aldo.hiberus.prueba_tecnica.mapper.DeparmentMapper;
import com.aldo.hiberus.prueba_tecnica.repositories.DeparmentRepository;

@Service
public class DeparmentServiceImpl implements DeparmentService{

    private static Logger logger = LoggerFactory.getLogger(DeparmentServiceImpl.class);
    private final DeparmentRepository deparmentRepository;
    private final DeparmentMapper deparmentMapper;

    public DeparmentServiceImpl(DeparmentRepository deparmentRepository, DeparmentMapper deparmentMapper){
        this.deparmentRepository = deparmentRepository;
        this.deparmentMapper = deparmentMapper;
    }

    @Override
    public List<DeparmentDTO> findAll() {
        List<Department> deparments = (List<Department>) this.deparmentRepository.findAll();
        return deparments.stream()
                .map(department -> this.deparmentMapper.toDeparmentDTO(department))
                .toList();
    }

    @Override
    @Transactional
    public void save(DeparmentDTO deparmentDTO) {
        try {
            this.deparmentRepository.save(this.deparmentMapper.toDepartment(deparmentDTO));
            logger.info("Departamento guardado..!!");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            this.deparmentRepository.findById(id).ifPresent(deparment -> {
                this.deparmentRepository.deleteDeparment(this.deparmentMapper.toDeparmentDTO(deparment).getId());
                logger.info("Departamento eliminado..!!");
            });
        } catch (Exception e) {
           logger.error(e.getMessage());
        }
    }

}
