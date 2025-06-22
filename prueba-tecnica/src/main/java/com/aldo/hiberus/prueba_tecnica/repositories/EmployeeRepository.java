package com.aldo.hiberus.prueba_tecnica.repositories;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aldo.hiberus.prueba_tecnica.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Modifying
    @Query("update Employee e set e.enabled='I' where e.id=?1")
    void deleteEmployee(Long id);

}
