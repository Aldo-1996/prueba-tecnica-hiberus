-- Departamentos
INSERT INTO departments (name, enabled) VALUES ('Sistemas', 'A');
INSERT INTO departments (name, enabled) VALUES ('Contabilidad', 'A');
INSERT INTO departments (name, enabled) VALUES ('RRHH', 'I');
INSERT INTO departments (name, enabled) VALUES ('People', 'A');

-- Empleados
INSERT INTO employees (names, surnames, age, role, salary, entry_date, departure_date, enabled, department_id) VALUES
('Alfredo', 'Campos', 35, 'Desarrollador', 1700, '2021-02-10', null, 'A', 1),
('Aldo', 'Carrillo', 28, 'Desarrollador', 900, '2025-05-30', null, 'A', 1),
('Ana', 'Maldonado', 29, 'Financiero', 900, '2020-03-11', '2024-05-20', 'I', 2),
('Fabricio', 'Arias', 25, 'Financiero', 600, '2025-05-25', null, 'A', 2);