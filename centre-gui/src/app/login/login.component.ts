import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;

  constructor(private authenticationService: AuthenticationService) {
    this.username = '';
    this.password = '';
  }

  ngOnInit() {
  }

  login() {
    this.authenticationService.login(this.username, this.password);
    this.username = '';
    this.password = '';
  }
}
