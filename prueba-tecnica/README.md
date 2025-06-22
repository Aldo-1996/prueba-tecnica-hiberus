# Sistema de Gesti贸n de Empleados y Departamentos

Este proyecto es una aplicaci贸n de backend construida con **Spring Boot** y **base de datos H2** en memoria, para registro de empleados y mostrar datos reelevantes de los mismos.

## 馃搫 Herramientas tecnol贸gicas usadas

* Java 17
* Spring Boot 3.5.0
* Spring Web
* Spring Data JPA
* H2 Database
* Maven
* JUnit 5 y Mockito
* Map Struct

---

## 鈿欙笍 Ejecuci贸n del Proyecto

### 1. Clonar el repositorio:

```bash
git clone <url-del-repositorio>
```

### 2. Ejecutar el proyecto (consideraciones):

* Realizar un **mvn clean install**
* Ejecutar el archivo **launch-compose.sh** que levanta los contenedores necesarios para inicializar la aplicaci贸n.
* Este proyecto tiene una parte Front End en Angular v15.0.4 donde se tienen las pantallas que realizan solicitudes a este Backend


### 3. Acceso a la consola H2:

```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
```

---

## 馃攳 Endpoints Disponibles

### DepartmentController:

* `POST /department` 鈥?Crea un nuevo departamento
* `POST /department/{deparmentId}` 鈥?Eliminaci贸n l贸gica de un departamento

### EmployeeController:

* `POST /employee` 鈥?Crea un nuevo empleado asociado a un departamento
* `POST /employee/{employeeId}` 鈥?Eliminaci贸n l贸gica del empleado
* `GET /employee/highestSalary` 鈥?Devuelve empleado con salario m谩s alto
* `GET /employee/lowerAge` 鈥?Devuelve el empleado m谩s joven
* `GET /employee/countLastMonth` 鈥?N煤mero de empleados ingresados en el 煤ltimo mes

## 馃敡 Consideraciones T茅cnicas

* Se utilizan **Streams** para operaciones en las APIs (highestSalary, lowerAge, countLastMonth).
* Se usa patr贸n de dise帽o DTO para separar responsabilidades entre capa de persistencia y presentaci贸n.
* Al inicializar el proyecto se precargan datos de empleados y departamentos desde el archivo **data.sql**.
* Se usa la librer铆a Map Struct para el mapeo entre objetos (entidades y DTOs).


## 馃殌 Autor

**\[Aldo Carrillo]**
