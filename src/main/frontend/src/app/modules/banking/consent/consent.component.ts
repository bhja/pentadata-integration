import {Component, Inject, Input, OnInit} from '@angular/core';
import {PersonService} from "../../../service";

import {MatCheckboxChange} from "@angular/material/checkbox";
import {ViewService} from "../../../service/view-service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-consent',
  templateUrl: './consent.component.html',
  styleUrls: ['./consent.component.css']
})
export class ConsentComponent implements OnInit {

  checked = false;
  postUrl: any;
  text: any = "By linking accounts you will authorize access to your data. \n" +
    "   Profile Data and Statement Data " +
    "\n" +
    "By clicking \"Share my data\", I allow this third party to access and retrieve my information."

  constructor(private active: ActivatedRoute, private router: Router, private viewService: ViewService, private personService: PersonService) {
  }

  ngOnInit(): void {
    //Remove the older signatures if any.
    localStorage.removeItem('signature');
    const personId: any = localStorage.getItem('personId');
    this.personService.getBankingConsent(personId).subscribe(
      (data => {
        this.postUrl = data.response.consent_post_url;
        localStorage.setItem('signature', data.response.signature);
      })
    );

  }


  sendUserConsent(event: MatCheckboxChange): void {
    this.checked = event.checked;
  }

  launch(): void {

    const signature: any = localStorage.getItem('signature');
    const personId: any = localStorage.getItem('personId');

    this.personService.sendConsent(signature, personId, this.text, this.postUrl).subscribe(
      (data) => {
        this.viewService.setConsentView(false);
      });
  }
}
