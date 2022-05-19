import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {AccountRoutingModule} from "./modules/accounts/account-routing.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";

import {LoginModule} from "./modules/login/login.module";

import { ScrollingModule } from '@angular/cdk/scrolling';
import {LoginRoutingModule} from "./modules/login/login-routing.module";
import {AccountService, InstitutionsService, PersonService, UserService} from "./service";

import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {BankingModule} from "./modules/banking/banking-module";
import {AccountModule} from "./modules/accounts/account.module";
import {AddBankModule} from "./modules/banking/add-bank/add-bank.module";
import {TransactionsModule} from "./modules/accounts/transactions/transactions.module";
import {MatTabsModule} from "@angular/material/tabs";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";






@NgModule({
    declarations: [
        AppComponent

    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        AccountRoutingModule,
        HttpClientModule,
        LoginModule,
        LoginRoutingModule,
        ScrollingModule,
      MatTableModule,
      MatIconModule,
      BankingModule,
      AccountModule,
      AddBankModule,
      TransactionsModule,
      MatTabsModule,
      CommonModule,
      FormsModule



    ],
    providers: [
      InstitutionsService,
      PersonService,
      AccountService,
      UserService

    ],
    exports: [

    ],
    bootstrap: [AppComponent]
})
export class AppModule {


}
