package com.aldo.hiberus.prueba_tecnica.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aldo.hiberus.prueba_tecnica.entities.dto.DeparmentDTO;
import com.aldo.hiberus.prueba_tecnica.services.DeparmentService;

@RestController
@RequestMapping("/department")
@CrossOrigin("http://localhost:4200")
public class DepartmentController {

    private final DeparmentService deparmentService;

    public DepartmentController(DeparmentService deparmentService){
        this.deparmentService = deparmentService;
    }

    @GetMapping
    public ResponseEntity<?> getAllDepartments(){
        return ResponseEntity.status(HttpStatus.OK).body(this.deparmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> saveDepartment(@RequestBody DeparmentDTO deparmentDTO){
        //DeparmentDTO deparmentDTO = this.deparmentMapper.toDeparmentDTO(department);
        this.deparmentService.save(deparmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Nuevo departamento creado"));
    }

    @PostMapping("/{deparmentId}")
    public ResponseEntity<?> deleteDeparment(@PathVariable Long deparmentId){
        this.deparmentService.delete(deparmentId);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Departamento eliminado"));
    }

}
