package com.aldo.hiberus.prueba_tecnica.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aldo.hiberus.prueba_tecnica.entities.Department;

public interface DeparmentRepository extends CrudRepository<Department, Long>{

    @Modifying
    @Query("update Department d set d.enabled='I' where id=?1")
    void deleteDeparment(Long id);

}
