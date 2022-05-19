import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AccountService} from "../../service";
import {Account} from "../../model/account";
import {MatTableDataSource} from "@angular/material/table";

import {ActivatedRoute, Router} from "@angular/router";
import {ViewService} from "../../service/view-service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit,AfterViewInit {


  selectedRowIndex = -1;
  columns:string[] = ['bank','account_name','account_type','delete'];
  accounts:Account[] = [];
  loading = false;

  dataSource = new MatTableDataSource<Account> ([]);
  constructor(private route:ActivatedRoute,private router:Router,private accountService:AccountService,private viewService:ViewService) { }

  ngOnInit(): void {
    this.getAccounts();
  }

  ngAfterViewInit(): void {
  }

  delete(rowid: number){
   let account= this.accounts[rowid];
   console.log("Delete action");
  }


  getAccounts(){
    this.loading = true;
    //This is hack. It would not be so in the actual implementation.
    const userId:any =localStorage.getItem("userId")  ;
    const personId:any = localStorage.getItem('personId');

    this.accountService.getAccounts(personId,userId).subscribe((data)=>{
      this.loading = false;
      this.dataSource = new MatTableDataSource <Account> (data); //pass the array you want in the table
      this.accounts = data;
      this.viewService.setAccount(this.accounts.length);
    });
  }

  showTransactions(account:Account){
   this.router.navigate(["/transactions"],{relativeTo:this.route,queryParams:{accountId:account.account_id,accountName:account.account_name}});


  }
  addAccount() : void {
    this.router.navigate(['/banking'],
      {relativeTo: this.route, queryParams: {action: 'add'}});
  }
}
