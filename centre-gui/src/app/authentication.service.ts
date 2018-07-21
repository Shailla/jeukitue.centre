import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {catchError, map, tap} from 'rxjs/operators';

import {User} from './users/user';

@Injectable()
export class AuthenticationService {

  private authServiceBaseUrl = '/auth/';

  private authenticated: boolean;

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<any> {
    //    return this.http.post<any>(this.authServiceBaseUrl + 'login', {username: username, password: password}).pipe(
    //      catchError(this.handleError)
    //    );
    
    const headers = new HttpHeaders({
      authorization: 'Basic ' + btoa(username + ':' + password)
    });

    return this.http.post(this.authServiceBaseUrl + 'login', {headers: headers}).pipe(
      catchError(this.handleError)
    );
  }

  logout() {
    return this.http.post<any>(this.authServiceBaseUrl + 'logout', {}).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
