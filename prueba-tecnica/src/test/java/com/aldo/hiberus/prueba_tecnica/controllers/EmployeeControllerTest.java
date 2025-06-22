package com.aldo.hiberus.prueba_tecnica.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.aldo.hiberus.prueba_tecnica.entities.Employee;
import com.aldo.hiberus.prueba_tecnica.entities.dto.EmployeeDTO;
import com.aldo.hiberus.prueba_tecnica.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllEmployeesTest() throws Exception{
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setNames("Fernando");
        employeeDTO1.setSurnames("Gago");
        employeeDTO1.setSalary(800.0);
        employeeDTO1.setEnabled("A");

        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setNames("Diana");
        employeeDTO2.setSurnames("Fernandez");
        employeeDTO2.setSalary(1200.0);
        employeeDTO2.setEnabled("A");

        when(employeeService.findAll()).thenReturn(List.of(employeeDTO1, employeeDTO2));

        mockMvc.perform(get("/employee"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].names").value("Fernando"))
        .andExpect(jsonPath("$[0].surnames").value("Gago"))
        .andExpect(jsonPath("$[0].salary").value(800.0))
        .andExpect(jsonPath("$[0].enabled").value("A"))

        .andExpect(jsonPath("$[1].names").value("Diana"))
        .andExpect(jsonPath("$[1].surnames").value("Fernandez"))
        .andExpect(jsonPath("$[1].salary").value(1200.0))
        .andExpect(jsonPath("$[1].enabled").value("A"));


    }

    @Test
    void saveEmployeeTest() throws Exception {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setNames("Ana");
        employeeDTO.setSurnames("Maldonado");
        employeeDTO.setAge(29);
        employeeDTO.setRole("Financiero");
        employeeDTO.setSalary(1200.0);
        employeeDTO.setEntryDate(LocalDate.of(2022, 9, 2));
        employeeDTO.setEnabled("A");

        doNothing().when(employeeService).createEmployee(employeeDTO);

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Empleado registrado correctamente"));

    }

    @Test
    void removeEmployeeTest() throws Exception {

        Long employeeId = 1L;

        doNothing().when(employeeService).deleteEmployee(employeeId);

        mockMvc.perform(post("/employee/" + employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").value("Empleado removido correctamente"));

        verify(employeeService).deleteEmployee(employeeId);

    }

    @Test
    void highestSalaryEmployeeTest() throws Exception {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setNames("Aldo");
        employeeDTO.setSalary(1500.0);
        employeeDTO.setEnabled("A");

        when(employeeService.highestSalaryEmployee("A")).thenReturn(Optional.of(employeeDTO));

        mockMvc.perform(get("/employee/highestSalary"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.names").value("Aldo"))
            .andExpect(jsonPath("$.salary").value(1500.0));

    }

    @Test
    void lowerAgeEmployeeTest() throws Exception {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setNames("Maria");
        employeeDTO.setAge(21);
        employeeDTO.setEnabled("A");

        when(employeeService.lowerAgeEmployee("A")).thenReturn(Optional.of(employeeDTO));

        mockMvc.perform(get("/employee/lowerAge"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.names").value("Maria"));

    }

    @Test
    void countLastMonthEmployeeTest() throws Exception {

        Employee employee1 = new Employee();
        employee1.setNames("Fernando");
        employee1.setEnabled("A");
        employee1.setEntryDate(LocalDate.of(2025, 5, 25));

        Employee employee2 = new Employee();
        employee2.setNames("Fernando");
        employee2.setEnabled("A");
        employee2.setEntryDate(LocalDate.of(2025, 5, 25));

        Employee employee3 = new Employee();
        employee3.setNames("Talia");
        employee3.setEnabled("A");
        employee3.setEntryDate(LocalDate.of(2025, 5, 25));

        List<Employee> employees = List.of(employee1, employee2, employee3);

        when(employeeService.countLastMonthEmployee("A")).thenReturn(employees.size());

        mockMvc.perform(get("/employee/countLastMonth"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.numberEmployees").value(3));


    }
    

}
