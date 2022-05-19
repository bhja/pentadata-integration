import * as moment from "moment";
import {Location} from "./location";
export class Transaction{
  amount : number;
  description : string;
  merchantName : string;
  category : string;
  pending : boolean;
  currency: string;
  location: Location;
  datetime: string;



  constructor(obj?: any) {
    Object.assign(this, obj);

  }



}
