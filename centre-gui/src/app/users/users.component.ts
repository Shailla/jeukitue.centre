import { Component, OnInit } from '@angular/core';

import { User } from './user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users: User[];
  selectedUser: User;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getUsers().subscribe(users => this.users = users);
  }

  onSelect(userLogin: string) {
    console.log('User selected : ', userLogin);
    this.userService.getUser(userLogin).subscribe(user => this.selectedUser = user);
  }
}
