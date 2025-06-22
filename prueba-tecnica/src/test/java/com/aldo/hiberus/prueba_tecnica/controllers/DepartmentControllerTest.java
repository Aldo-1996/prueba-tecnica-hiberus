package com.aldo.hiberus.prueba_tecnica.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.aldo.hiberus.prueba_tecnica.entities.dto.DeparmentDTO;
import com.aldo.hiberus.prueba_tecnica.services.DeparmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeparmentService deparmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllDepartmentsTest() throws Exception{
        DeparmentDTO deparmentDTO1 = new DeparmentDTO();
        deparmentDTO1.setName("Sistemas");

        DeparmentDTO deparmentDTO2 = new DeparmentDTO();
        deparmentDTO2.setName("RRHH");

        when(deparmentService.findAll()).thenReturn(List.of(deparmentDTO1, deparmentDTO2));

        mockMvc.perform(get("/department"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Sistemas"))
        .andExpect(jsonPath("$[1].name").value("RRHH"));

    }

    @Test
    void saveDepartmentTest() throws Exception {
        DeparmentDTO deparmentDTO = new DeparmentDTO();
        deparmentDTO.setName("Sistemas");
        deparmentDTO.setEnabled("A");

        doNothing().when(deparmentService).save(deparmentDTO);

        mockMvc.perform(post("/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deparmentDTO))
                    )
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.message").value("Nuevo departamento creado"));

    }

    @Test
    void deleteDeparmentTest() throws Exception {
        Long deparmentId = 1L;

        doNothing().when(deparmentService).delete(deparmentId);

        mockMvc.perform(post("/department/" + deparmentId)
                        .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.message").value("Departamento eliminado"));

        verify(deparmentService).delete(deparmentId);
    }

}
