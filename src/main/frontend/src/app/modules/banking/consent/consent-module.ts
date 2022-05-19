import { NgModule } from '@angular/core';

import {ConsentComponent} from "./consent.component";
import {MatCardModule} from "@angular/material/card";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {CommonModule} from "@angular/common";
import {MatDialogModule} from "@angular/material/dialog";


@NgModule({
  declarations: [ConsentComponent],
    imports: [
        CommonModule,
        MatCardModule,
        MatCheckboxModule,
        MatDialogModule
    ],
  providers: [

  ],
  exports:[ConsentComponent]
})
export class ConsentModule {

}
