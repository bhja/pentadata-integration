// Core modules
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {BankingComponent} from "./banking.component";


const routes: Routes = [
  {
    path: 'banking',
    component: BankingComponent
  }
];

@NgModule({
  imports: [ RouterModule.forChild(routes) ],
  exports: [ RouterModule ]
})
export class BankingRoutingModule { }
