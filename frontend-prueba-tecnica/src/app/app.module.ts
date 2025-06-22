import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { FormEmployeesComponent } from './components/form-employees/form-employees.component';
import { MenuComponent } from './components/menu/menu.component';
import { InfoApiComponent } from './components/info-api/info-api.component';
import { ListEmployeesComponent } from './components/list-employees/list-employees.component';

@NgModule({
  declarations: [
    AppComponent,
    FormEmployeesComponent,
    MenuComponent,
    InfoApiComponent,
    ListEmployeesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
