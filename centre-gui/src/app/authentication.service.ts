import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import 'rxjs/add/operator/finally';

@Injectable()
export class AuthenticationService {
    authenticated = false;

    private registerUrl:string = '/auth/register';
    private loginUrl:string = '/auth/login';
    private logoutUrl:string = '/auth/logout';

    constructor(private http: HttpClient) {
        console.log('CONSTRUCTOR');
    }

    register(username: string, password: string) {
        this.http.post(this.registerUrl, {}).subscribe(
            (response) => console.log(response)
        );
    }

    login(username: string, password: string, callback) {
        const headers = new HttpHeaders({
        authorization: 'Basic ' + btoa(username + ':' + password)
        });

        this.http.get(this.loginUrl, {headers: headers}).subscribe(
            response => {
                if(response['name']) {
                    this.authenticated = true;
                }
                else {
                    this.authenticated = false;
                }

                return callback && callback();
            }
        );
    }

    logout() {
        this.http.post(this.logoutUrl, {}).finally(() => {
            this.authenticated = false;
        }).subscribe();
    }
}
