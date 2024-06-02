import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../services/user.service";
import {CommonModule, DatePipe, NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    DatePipe,
    NgForOf,
    NgIf,
    RouterLink,CommonModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  public users: User[] = [];
  constructor(private userService: UserService) {
  }

  ngOnInit() {
  }

  getAllUsers(): void {
    this.userService.getUsers().subscribe({
      next: data => {
        this.users = data;
        console.log(data);
      }
    });
  }

}
