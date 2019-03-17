import { Component, OnInit } from '@angular/core';

import { User } from './user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  pageNumber: number = 1;
  pageSize: number = 5;
  total: number;
  users: User[];
  selectedUser: User;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers(): void {
    if(this.pageNumber < 1) {
        this.pageNumber = 1;
    }

    this.userService.getUsers(this.pageNumber-1, this.pageSize).subscribe(res => {
        this.total = res.total;
        this.users = res.users;
    });

    let maxPage = Math.floor(this.total / this.pageSize + 1);

    if(this.pageNumber > maxPage) {
        this.pageNumber = maxPage;
    }
  }

  getTotalPage(): number {
    return Math.floor(this.total / this.pageSize + 1);
  }

  showNextPage() {
    this.pageNumber++;
    this.getUsers();
  }

  showPreviousPage() {
    this.pageNumber--;
    this.getUsers();
  }

  showPage(pageNumber: number) {
    this.pageNumber = pageNumber;
    this.getUsers();
  }

  onSelect(userLogin: string) {
    console.log('User selected : ', userLogin);
    this.userService.getUser(userLogin).subscribe(user => this.selectedUser = user);
  }
}
