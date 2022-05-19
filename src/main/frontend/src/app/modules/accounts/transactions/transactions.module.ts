import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import {FormsModule, ReactiveFormsModule} from "@angular/forms";


import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatDialogModule} from "@angular/material/dialog";
import {MAT_FORM_FIELD_DEFAULT_OPTIONS} from '@angular/material/form-field';
import {MAT_SELECT_SCROLL_STRATEGY_PROVIDER, MatSelectModule} from '@angular/material/select';
import {MatCardModule} from "@angular/material/card";
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from "@angular/material/button";
import {
  MatAutocompleteModule
} from "@angular/material/autocomplete";

import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {TransactionsComponent} from "./transactions.component";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";
import {Transaction} from "../../../model/transaction";


@NgModule({
  declarations: [TransactionsComponent],
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
    MatAutocompleteModule,
    MatTableModule,
    MatIconModule,
    MatPaginatorModule

  ],
  exports: [
    TransactionsComponent
  ],
  providers: [
    {provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {float: 'always'}}


  ]
})
export class TransactionsModule {

}
