import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { catchError, map, tap } from 'rxjs/operators';

import { User } from './users/user';

@Injectable()
export class UserService {

  private userServiceBaseUrl = '/admin/user/';
  private usersServiceUrl = this.userServiceBaseUrl + 'list';   // Get players list in current Map

  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
  return this.http.get<User[]>(this.usersServiceUrl)
    .pipe(
      catchError(this.handleError)
    );
}

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
