import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormEmployeesComponent } from './components/form-employees/form-employees.component';
import { InfoApiComponent } from './components/info-api/info-api.component';
import { ListEmployeesComponent } from './components/list-employees/list-employees.component';

const routes: Routes = [
   { path: 'registro', component: FormEmployeesComponent },
   { path: 'info-api', component: InfoApiComponent },
   { path: 'list-employees', component: ListEmployeesComponent },
   { path: '', redirectTo: '/registro', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
