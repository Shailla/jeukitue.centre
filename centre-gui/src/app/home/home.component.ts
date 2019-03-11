import { Component, OnInit } from '@angular/core';

import {AuthenticationService} from '../authentication.service'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  authenticated(): boolean {
      console.log('VALEUR:' + this.authenticationService.authenticated);
      return this.authenticationService.authenticated;
  }

  logout() {
      this.authenticationService.logout();
  }
}
