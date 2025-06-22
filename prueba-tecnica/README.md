# Sistema de Gestión de Empleados y Departamentos

Este proyecto es una aplicación de backend construida con **Spring Boot** y **base de datos H2** en memoria, para registro de empleados y mostrar datos reelevantes de los mismos.

## 📄 Herramientas tecnológicas usadas

* Java 17
* Spring Boot 3.5.0
* Spring Web
* Spring Data JPA
* H2 Database
* Maven
* JUnit 5 y Mockito
* Map Struct

---

## ⚙️ Ejecución del Proyecto

### 1. Clonar el repositorio:

```bash
git clone <url-del-repositorio>
```

### 2. Ejecutar el proyecto (consideraciones):

* Ejecutar el archivo **launch-compose.sh** que levanta los contenedores necesarios para inicializar la aplicación.
* Este proyecto tiene una parte Front End en Angular v15.0.4 donde se tienen las pantallas que realizan solicitudes a este Backend


### 3. Acceso a la consola H2:

```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
```

---

## 🔍 Endpoints Disponibles

### DepartmentController:

* `POST /department` – Crea un nuevo departamento
* `POST /department/{deparmentId}` – Eliminación lógica de un departamento

### EmployeeController:

* `POST /employee` – Crea un nuevo empleado asociado a un departamento
* `POST /employee/{employeeId}` – Eliminación lógica del empleado
* `GET /employee/highestSalary` – Devuelve empleado con salario más alto
* `GET /employee/lowerAge` – Devuelve el empleado más joven
* `GET /employee/countLastMonth` – Número de empleados ingresados en el último mes

## 🔧 Consideraciones Técnicas

* Se utilizan **Streams** para operaciones en las APIs (highestSalary, lowerAge, countLastMonth).
* Se usa patrón de diseño DTO para separar responsabilidades entre capa de persistencia y presentación.
* Al inicializar el proyecto se precargan datos de empleados y departamentos desde el archivo **data.sql**.
* Se usa la librería Map Struct para el mapeo entre objetos (entidades y DTOs).


## 🚀 Autor

**\[Aldo Carrillo]**
