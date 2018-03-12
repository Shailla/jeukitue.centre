import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    return this.http.post<any>('/login', {username: username, password: password});
  }

  logout() {
    
  }
}
