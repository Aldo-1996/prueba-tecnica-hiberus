import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { departments } from '../interfaces/departments';

@Injectable({
  providedIn: 'root'
})
export class DepartmentsService {

  private url = 'http://localhost:8080/department';

  constructor(private httpClient: HttpClient) { }


  getDepartments(): Observable<departments[]> {
    return this.httpClient.get<departments[]>(`${this.url}`);
  }

}
