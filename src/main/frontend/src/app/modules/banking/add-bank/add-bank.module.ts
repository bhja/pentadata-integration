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
import {AddBankComponent} from "./add-bank.component";
import {
  MAT_AUTOCOMPLETE_SCROLL_STRATEGY,
  MAT_AUTOCOMPLETE_DEFAULT_OPTIONS_FACTORY,
  MatAutocompleteModule, MAT_AUTOCOMPLETE_SCROLL_STRATEGY_FACTORY_PROVIDER
} from "@angular/material/autocomplete";
import {ConsentModule} from "../consent/consent-module";
import {MAT_AUTOCOMPLETE_SCROLL_STRATEGY_FACTORY} from "@angular/material/autocomplete/autocomplete-trigger";

import { Overlay, CloseScrollStrategy } from '@angular/cdk/overlay';
import {AccountModule} from "../../accounts/account.module";
import {MatIcon, MatIconModule} from "@angular/material/icon";

export function scrollFactory(overlay: Overlay): () => CloseScrollStrategy {
  return () => overlay.scrollStrategies.close();
}

@NgModule({
  declarations: [AddBankComponent],
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
    ConsentModule,
    AccountModule,
    MatIconModule
  ],
  exports: [
    AddBankComponent
  ],
  providers: [
    {provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {float: 'always'}}


  ]
})
export class AddBankModule {

}
