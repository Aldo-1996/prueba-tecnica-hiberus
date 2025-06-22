import { Component, OnInit } from '@angular/core';
import { employee } from 'src/app/interfaces/employees';
import { EmployeesService } from 'src/app/services/employees.service';

@Component({
  selector: 'app-list-employees',
  templateUrl: './list-employees.component.html',
  styleUrls: ['./list-employees.component.css']
})
export class ListEmployeesComponent implements OnInit{
  employees: employee[] = [
    {
      names: '',
      surnames: '',
      age: 0,
      role: '',
      salary: 0,
      entryDate: '',
      department: {
        id: 0,
        name: '',
        enabled: ''
      },
      enabled: ''
    },
  ];

  constructor(private employeeService: EmployeesService){}
  
  ngOnInit(): void {
    this.employeeService.getAllEmployees()
    .subscribe(res => {
      if (res) {
        this.employees = res;
      }
    });
  }
}
