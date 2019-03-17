import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import {AuthenticationService} from '../authentication.service'

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  username: string;
  password: string;

  userAuthenticated = false;
  userLogin: string;

    constructor(private authService: AuthenticationService, private router: Router) {
        this.username = '';
        this.password = '';

        this.authService.login(undefined, undefined, undefined);
        this.userAuthenticated = this.authService.userAuthenticated;
        this.userLogin = this.authService.userLogin;
    }

  ngOnInit() {
  }

  login() {
    this.authService.login(this.username, this.password, () => {
      this.userAuthenticated = this.authService.userAuthenticated;
      this.userLogin = this.authService.userLogin;

      this.router.navigateByUrl('/dashboard');
    });

    this.username = '';
    this.password = '';
  }

  logout() {
    this.userAuthenticated = false;
    this.userLogin = '';

    this.authService.logout();
  }
}
