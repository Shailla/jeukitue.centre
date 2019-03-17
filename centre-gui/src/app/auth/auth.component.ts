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

    constructor(private authService: AuthenticationService, private router: Router) {
        this.username = '';
        this.password = '';
    }

  ngOnInit() {
  }

  login() {
    this.authService.login(this.username, this.password, () => {
        this.router.navigateByUrl('/dashboard');
    });

    this.username = '';
    this.password = '';
  }

  logout() {
      this.authService.logout();
  }
}
