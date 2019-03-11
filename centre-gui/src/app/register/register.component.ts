import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    username: string;
    password: string;

    constructor(private authenticationService: AuthenticationService, private router: Router) {
        this.username = '';
        this.password = '';
    }

    login() {
        this.authenticationService.register(this.username, this.password);
        this.username = '';
        this.password = '';
    }

    ngOnInit() {
    }
}
