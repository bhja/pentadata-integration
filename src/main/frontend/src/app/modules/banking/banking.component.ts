import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PersonService} from "../../service";

@Component({
  selector: 'app-banking',
  templateUrl: './banking.component.html',
  styleUrls: ['./banking.component.css']
})
export class BankingComponent implements OnInit {
    add = false;
    account = false;
    link = false;
  constructor(private  active:ActivatedRoute,private personService:PersonService) {

    active.queryParams.subscribe(params => {
        let action = params['action'];
        if (action == 'add') {
          this.add = true;
          this.account = false;
          this.link = false;
        }else if(action =='account'){
          this.add = false;
          this.account = true;
          this.link = false;
        }else if(action =='link'){
          this.link = true;
        }
      }
    );
  }

  ngOnInit(): void {

  }

  create() : void{
    let userId:any = localStorage.getItem('userId');
    this.personService.createPerson(userId).subscribe(
      data=> {
        localStorage.setItem('personId',data.response.person_id);
        this.add = true;
        this.link = false;
      });

  }

}
