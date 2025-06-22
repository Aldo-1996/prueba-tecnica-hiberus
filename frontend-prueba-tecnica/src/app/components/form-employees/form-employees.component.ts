import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { filter, map } from 'rxjs';
import { departments } from 'src/app/interfaces/departments';
import { employee } from 'src/app/interfaces/employees';
import { DepartmentsService } from 'src/app/services/departments.service';
import { EmployeesService } from 'src/app/services/employees.service';

@Component({
  selector: 'form-employees',
  templateUrl: './form-employees.component.html',
  styleUrls: ['./form-employees.component.css']
})
export class FormEmployeesComponent implements OnInit{
  public registerForm: FormGroup;
  public currentDate: string = '';
  public departments: departments[] = [{
    id: 0,
    name: '',
    enabled: 'A'
  }];

  public showConfirmMessage: boolean = false;
  public hideConfirmMessage: boolean = false;

  constructor(private fb: FormBuilder,
              private departmentService: DepartmentsService,
              private employeeService: EmployeesService) 
  {
    this.registerForm = this.fb.group({
      names: ['', Validators.required],
      surnames: ['', Validators.required],
      age: [null, [Validators.required, Validators.min(18)]],
      role: ['', Validators.required],
      salary: [null, [Validators.required, Validators.min(0)]],
      entryDate: ['', Validators.required],
      departureDate: [''],
      department: ['', Validators.required]
    });

    const today = new Date();
    const offsetUTC5 = -5 * 60;
    const utc5Time = new Date(today.getTime() + (today.getTimezoneOffset() + offsetUTC5) * 60000);
    this.currentDate = utc5Time.toISOString().split('T')[0];

  }
  ngOnInit(): void {
    this.departmentService.getDepartments()
    .subscribe(res => {
      if (res) {
        this.departments = res.filter(department => department.enabled === 'A');
      }
    })
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const dataform = this.registerForm.value;
      const objEmployee: employee = {
        names: dataform.names,
        surnames: dataform.surnames,
        age: Number(dataform.age),
        role: dataform.role,
        salary: Number(dataform.salary),
        entryDate: dataform.entryDate,
        departureDate: dataform.departureDate ? dataform.departureDate : null,
        department: {
          id: Number(dataform.department)
        },
        enabled: 'A'
      }
      //console.log(objEmployee);
      this.employeeService.saveEmployee(objEmployee)
      .subscribe(res => {
        this.showConfirmMessage = true;
        this.hideConfirmMessage = false;
      }, () => {},
      () => {
        setTimeout(() => {
          this.hideConfirmMessage = true;
          setTimeout(() => {
            this.showConfirmMessage = false;
          }, 500);
        }, 1000);
        this.registerForm.reset();
      });
    } else {
      this.registerForm.markAllAsTouched();
    }
  }
}
