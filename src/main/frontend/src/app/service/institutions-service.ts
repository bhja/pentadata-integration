import {Injectable} from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {BaseHttpService} from './base-http-service';
import {environment} from "../../environments/environment";

import {Institution} from "../model/institution";

@Injectable()
export class InstitutionsService extends BaseHttpService{

  constructor(http: HttpClient
              ) {
    super(http);
  }
  /**
   *
   */
  institutions(filters:string): Observable<Institution[]> {

    const url = environment.api.institutions.url;

    return this.http.get<Institution[]>(url+filters)
      .pipe(
        map(response => {
          return response;
        }),
        catchError(this.handleError)
      );
  }


}
