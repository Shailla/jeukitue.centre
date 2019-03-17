import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import 'rxjs/add/operator/finally';
import { AppComponent } from './app.component';

@Injectable()
export class AuthenticationService {
    private registerUrl:string = '/rest/public/user/register';
    private loginUrl:string = '/rest/auth/login';
    private logoutUrl:string = '/rest/public/auth/logout';

    constructor(private app: AppComponent, private http: HttpClient) {
        console.log('CONSTRUCTOR');
    }

    register(login: string, password: string, mail: string) {
        let user = {
            login: login,
            password: password,
            mail: mail
        };

        this.http.post(this.registerUrl, user, {}).subscribe(
            (response) => console.log(response)
        );
    }

    login(login: string, password: string, callback) {
        const headers = new HttpHeaders({
            authorization: 'Basic ' + btoa(login + ':' + password)
        });

        this.http.get(this.loginUrl, {headers: headers}).subscribe(
            response => {
                if(response['name']) {
                    this.app.userAuthenticated = true;
                    this.app.userLogin = login;
                }
                else {
                    this.app.userAuthenticated = false;
                    this.app.userLogin = '';
                }

                return callback && callback();
            }
        );
    }

    logout() {
        this.http.post(this.logoutUrl, {}).finally(() => {
            this.app.userAuthenticated = false;
            this.app.userLogin = '';
        }).subscribe();
    }
}
