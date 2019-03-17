import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {catchError} from 'rxjs/operators';

import {User} from './users/user';

@Injectable()
export class UserService {

  private userServiceBaseUrl = '/rest/admin/user/';      // Services about users (create, remove, update, change password, ...)

  constructor(private http: HttpClient) {}

  getUsers(pageNumber: number, pageSize: number): Observable<any> {
    console.log('coucou 1');
    return this.http.get(this.userServiceBaseUrl + pageSize + '/' + pageNumber)
      .pipe(
        catchError(this.handleError)
      );
  }

  getUser(login: string): Observable<User> {
    return this.http.get<User>(this.userServiceBaseUrl + '/' + login)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
