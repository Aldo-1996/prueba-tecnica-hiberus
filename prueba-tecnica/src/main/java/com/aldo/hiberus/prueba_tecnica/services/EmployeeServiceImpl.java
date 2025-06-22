package com.aldo.hiberus.prueba_tecnica.services;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aldo.hiberus.prueba_tecnica.entities.Employee;
import com.aldo.hiberus.prueba_tecnica.entities.dto.EmployeeDTO;
import com.aldo.hiberus.prueba_tecnica.mapper.EmployeeMapper;
import com.aldo.hiberus.prueba_tecnica.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper){
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = (List<Employee>) this.employeeRepository.findAll();
        return employees.stream()
                .map(employee -> this.employeeMapper.toEmployeeDTO(employee))
                .toList();
    }

    @Override
    @Transactional
    public void createEmployee(EmployeeDTO employeeDTO) {
        try {
            this.employeeRepository.save(this.employeeMapper.toEmployee(employeeDTO));
            logger.info("Empleado registrado correctamente");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        try {
            this.employeeRepository.findById(id).ifPresent(employee -> {
                this.employeeRepository.deleteEmployee(this.employeeMapper.toEmployeeDTO(employee).getId());
                logger.info("Empleado eliminado correctamente");
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Optional<EmployeeDTO> highestSalaryEmployee(String enabled) {
        List<Employee> employees = (List<Employee>)this.employeeRepository.findAll();
        return employees.stream()
                .filter(employee -> "A".equals(employee.getEnabled()))
                .max(Comparator.comparing(Employee::getSalary))
                .map(employee -> Optional.of(this.employeeMapper.toEmployeeDTO(employee)))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<EmployeeDTO> lowerAgeEmployee(String enabled) {
        List<Employee> employees = (List<Employee>)this.employeeRepository.findAll();
        return employees.stream()
                .filter(employee -> "A".equals(employee.getEnabled()))
                .min(Comparator.comparing(Employee::getAge))
                .map(employee -> Optional.of(this.employeeMapper.toEmployeeDTO(employee)))
                .orElse(Optional.empty());
    }

    @Override
    public Integer countLastMonthEmployee(String enabled) {
        List<Employee> employees = (List<Employee>)this.employeeRepository.findAll();
        LocalDate today = LocalDate.now();
        LocalDate lastMonth = today.minusMonths(1);

        return (int) employees.stream()
        .filter(employee -> "A".equals(employee.getEnabled()))
        .filter(employee -> !employee.getEntryDate().isBefore(lastMonth) && !employee.getEntryDate().isAfter(today))
        .count();
    }

}
