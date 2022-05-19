import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { throwError, Observable, of } from 'rxjs';

// Environment
import { environment } from '../../environments/environment';


export class BaseHttpService {
  constructor(
    protected http: HttpClient
  ) { }

  /**
   * ???
   * @param error ???
   */
  protected handleError(error: HttpErrorResponse): Observable<any> {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      // this.logger.error('An error occurred: ' + error.error.message); // TODO
      console.error(`An error occurred: ${error.error.message} from URL ${error.url}`);
    }
    else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      // this.logger.error(`Backend returned code ${error.status}, body was: ${error.error}`); // TODO
      console.error(`Backend returned code ${error.status} for url ${error.url} with body:`, error.error);
    }

    // return an observable with a user-facing error message
    return throwError(error);
  }





  /**
   * ???
   * @param key ???
   * @param value ???
   */
  protected skipBody(key: string, value: any): any {
    return key === 'body' ? undefined : value;
  }

}
