import {Injectable} from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {BaseHttpService} from './base-http-service';
import {environment} from "../../environments/environment";



@Injectable()
export class UserService extends BaseHttpService{

  constructor(http: HttpClient
  ) {
    super(http);
  }
  /**
   *
   */
  getUser(emailid:string): Observable<any> {

    const url = environment.api.user.url;

    return this.http.get<any>(url+emailid)
      .pipe(
        map(response => {
          return response;
        }),
        catchError(this.handleError)
      );
  }


}
