import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    username: string;
    password: string;

    constructor(private authenticationService: AuthenticationService, private router: Router) {
        this.username = '';
        this.password = '';
    }

    login() {
        this.authenticationService.login(this.username, this.password, () => this.router.navigateByUrl('/home'));
        this.username = '';
        this.password = '';
    }

    ngOnInit() {
    }
}
