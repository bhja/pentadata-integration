import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import {FormsModule, ReactiveFormsModule} from "@angular/forms";

import {LoginComponent} from "./login.component";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatDialogModule} from "@angular/material/dialog";
import {MAT_FORM_FIELD_DEFAULT_OPTIONS} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatCardModule} from "@angular/material/card";
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from "@angular/material/button";
import {BankingRoutingModule} from "../banking/banking-routing.module";
import {TransactionsRoutingModule} from "../accounts/transactions/transactions-routing.module";
@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatDialogModule,
    MatSelectModule,
    MatCardModule,
    MatStepperModule,
    MatButtonModule,
    BankingRoutingModule,
    TransactionsRoutingModule
  ],
  providers: [
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {float: 'always'} }
  ]
})
export class LoginModule {

}
