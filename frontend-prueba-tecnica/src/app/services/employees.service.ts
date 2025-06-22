import { Injectable } from '@angular/core';
import { employee, numberEmployees } from '../interfaces/employees';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmployeesService {

  private url = 'http://localhost:8080/employee';

  constructor(private httpClient: HttpClient) { }

  saveEmployee(employee: employee): Observable<any> {
    return this.httpClient.post(this.url, employee);
  }

  getAllEmployees(): Observable<employee[]> {
    return this.httpClient.get<employee[]>(this.url);
  }

  getHighestSalaryEmployee(): Observable<employee> {
    return this.httpClient.get<employee>(this.url+'/highestSalary');
  }

  getLowerAgeEmployee(): Observable<employee> {
    return this.httpClient.get<employee>(this.url+'/lowerAge');
  }

  getCountLastMonthEmployee(): Observable<numberEmployees> {
    return this.httpClient.get<numberEmployees>(this.url+'/countLastMonth');
  }
}
