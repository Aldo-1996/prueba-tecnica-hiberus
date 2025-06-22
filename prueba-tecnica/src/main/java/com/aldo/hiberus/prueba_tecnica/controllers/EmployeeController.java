package com.aldo.hiberus.prueba_tecnica.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aldo.hiberus.prueba_tecnica.entities.dto.EmployeeDTO;
import com.aldo.hiberus.prueba_tecnica.services.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:4200")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(this.employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO employeeDTO){

        this.employeeService.createEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Empleado registrado correctamente"));

    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<?> removeEmployee(@PathVariable Long employeeId){
        this.employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Empleado removido correctamente"));
    }

    @GetMapping("/highestSalary")
    public ResponseEntity<?> highestSalaryEmployee(){
        Optional<EmployeeDTO> optionalEmployeeDTO = this.employeeService.highestSalaryEmployee("A");
        if (optionalEmployeeDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalEmployeeDTO.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se encontro ningun empleado"));
    }

    @GetMapping("/lowerAge")
    public ResponseEntity<?> lowerAgeEmployee(){
        Optional<EmployeeDTO> optionalEmployeeDTO = this.employeeService.lowerAgeEmployee("A");
        if (optionalEmployeeDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalEmployeeDTO.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se encontro ningun empleado"));
    }

    @GetMapping("/countLastMonth")
    public ResponseEntity<?> countLastMonthEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("numberEmployees", this.employeeService.countLastMonthEmployee("A")));
    }

}
