package com.aldo.hiberus.prueba_tecnica.entities.dto;

public class DeparmentDTO {

    private Long id;
    private String name;
    private String enabled = "A";

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEnabled() {
        return enabled;
    }
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
    

}
