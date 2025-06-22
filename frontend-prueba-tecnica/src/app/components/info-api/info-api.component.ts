import { Component, OnInit } from '@angular/core';
import { employee, numberEmployees } from 'src/app/interfaces/employees';
import { EmployeesService } from 'src/app/services/employees.service';

@Component({
  selector: 'app-info-api',
  templateUrl: './info-api.component.html',
  styleUrls: ['./info-api.component.css']
})
export class InfoApiComponent implements OnInit {
  
  public highestSalaryEmployee: employee = {
    names: '',
    surnames: '',
    age: 0,
    role: '',
    salary: 0,
    entryDate: '',
    departureDate: '', 
    department: {
        id: 0,
        name: '',
        enabled: ''
    },
    enabled: ''
  };
  public youngEmployee: employee = {
    names: '',
    surnames: '',
    age: 0,
    role: '',
    salary: 0,
    entryDate: '',
    departureDate: '', 
    department: {
        id: 0,
        name: '',
        enabled: ''
    },
    enabled: ''
  };
  public numberLastMonthEmployees: numberEmployees = {
    numberEmployees: 0
  };

  constructor(private employeeService: EmployeesService){}
  
  ngOnInit(): void {
    this.employeeService.getHighestSalaryEmployee()
    .subscribe(res => {
      if (res) {
        this.highestSalaryEmployee = res;
      }
    });

    this.employeeService.getLowerAgeEmployee()
    .subscribe(res => {
      if (res) {
        this.youngEmployee = res;
      }
    });

    this.employeeService.getCountLastMonthEmployee()
    .subscribe(res => {
      if (res) {
        this.numberLastMonthEmployees = res;
      }
    });
  }

}
