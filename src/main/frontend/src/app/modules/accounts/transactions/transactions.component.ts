import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Transaction} from "../../../model/transaction";
import {Location} from "../../../model/location";
import {AccountService} from "../../../service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatPaginator} from "@angular/material/paginator";
import * as moment from "moment";

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit,AfterViewInit {

  @ViewChild('paginator') paginator: MatPaginator;
  selectedRowIndex = -1;
  columns:string[] = ['amount','description','merchantName','pending','currency','datetime','location'];

  loading = false;
  transactions:Transaction[]=[];

  dataSource:MatTableDataSource<Transaction> = new MatTableDataSource<Transaction> ([]);
  accountId:any ;
  accountName:any;
  constructor(private  activeRoute:ActivatedRoute,private router:Router ,private accountService:AccountService
              ) {
    activeRoute.queryParams.subscribe(params => {
      this.accountId = params['accountId'];
      this.accountName = params['accountName'];

  });
  }

  ngOnInit(): void {
    const userId:any =localStorage.getItem("userId")  ;
    this.accountService.getTransactions(this.accountId,userId).subscribe((data)=>{
      this.dataSource = new MatTableDataSource<Transaction>(data);
      this.dataSource.paginator = this.paginator;
      this.transactions = data;
    });

  }

  ngAfterViewInit(): void {
  }

  back(): void{
    this.router.navigate(['/banking'],{relativeTo: this.activeRoute, queryParams: {action: 'account'}}
                         );

  }

  //Fetch whatever is to be displayed.
  showLocation(location:Location): string{
    let locationString :string = "";
     locationString = locationString.concat(location.country);
    return locationString;
  }

  showDate(date:string): string{
    let transactionDate = date.slice(0,4).concat("/").concat(date.slice(4,6)).concat("/")
      .concat(date.slice(6,8)).concat("/").concat(" ").concat(date.slice(8,10)).concat(":").concat(
        date.slice(10,12).concat(":").concat(date.slice(12,14))
      );
    return transactionDate;


  }

}
