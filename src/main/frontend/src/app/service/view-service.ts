import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ViewService {

  account = new BehaviorSubject<number>(0);

  accountState = this.account.asObservable();

  consentView = new BehaviorSubject<boolean>(true);

  consentState = this.consentView.asObservable();

  constructor() { }

  setAccount(val:number) : void{
    this.account.next(val);
  }

  setConsentView(val:boolean){
    this.consentView.next(val);
  }
}
