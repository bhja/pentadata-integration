import {Component, EventEmitter, Inject, OnInit, Output, ViewChild} from '@angular/core';
import {InstitutionsService, PersonService, UserService} from "../../service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {Person} from "../../model/person";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLinear = true;
  form: FormGroup;

  selectedValue: any;

  constructor(private route: ActivatedRoute, private router: Router, private dialog: MatDialog,
              private personService: PersonService, private userService: UserService
  ) {
    this.form = new FormBuilder().group({
      email: ['', Validators.required]
    });
  }

  ngOnInit(): void {

  }

  submit(form: FormGroup): void {
    let values = form.value;
    let person: Person = new Person();
    //Defaulting this to the mp user for pentadata behavior.
    person.first_name = "mp_user_fn";
    person.last_name = "mp_user_ln";
    person.email = values;


    this.userService.getUser(values.email).subscribe(data => {
      localStorage.setItem('userId', data.response.userId);
    });
    this.personService.getPerson(values.email).subscribe(data => {
      if (data?.response.person_id) {
        localStorage.setItem('personId',data.response.person_id);
        this.router.navigate(['/banking'],
          {relativeTo: this.route, queryParams: {action: 'account'}});
      } else {
        this.router.navigate(['/banking'],
          {relativeTo: this.route, queryParams: {action: 'link'}});
      }
    });
  }
}










