import { Component, OnInit } from '@angular/core';

import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    username: string;
    password: string;
    passwordConfirmation: string;
    mail: string;

    constructor(private authenticationService: AuthenticationService) {
        this.username = '';
        this.password = '';
        this.passwordConfirmation = '';
        this.mail = "";
    }

    register() {
        this.authenticationService.register(this.username, this.password, this.mail);
        this.username = '';
        this.password = '';
        this.passwordConfirmation = '';
        this.mail = '';
    }

    ngOnInit() {
    }
}
