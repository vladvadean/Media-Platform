import {Component, OnInit} from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {User} from "../../models/user";
import {Admin} from "../../models/admin";
import {AdminService} from "../../services/admin.service";

@Component({
  selector: 'app-sign-in-sign-up',
  templateUrl: './sign-in-sign-up.component.html',
  standalone: true,
  imports: [
    FormsModule
  ],
  styleUrls: ['./sign-in-sign-up.component.css']
})
export class SignInSignUpComponent implements OnInit {
  signInEmail: string = '';
  signInPassword: string = '';
  signUpUsername: string = '';
  signUpEmail: string = '';
  signUpPassword: string = '';
  user: User | null = null;
  admin: Admin | null = null;
  errorMessage: string = '';

  constructor(private adminService: AdminService, private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
    const signUpButton = document.getElementById('signUp');
    const signInButton = document.getElementById('signIn');
    const container = document.getElementById('container');

    signUpButton?.addEventListener('click', () => {
      container?.classList.add("right-panel-active");
    });

    signInButton?.addEventListener('click', () => {
      container?.classList.remove("right-panel-active");
    });
  }

  getUserWithCredentials(email: string, password: string): void {
    this.userService.getUserWithCredentials(email, password).subscribe({
      next: data => {
        this.user = data;
        this.userService.setUser(this.user); // Store user in the service
        this.router.navigate(['/account']);
      },
      error: err => {
        console.error('Sign in failed ' + email + " " + password);
        this.user = null;
        this.errorMessage = 'Sign in failed. Please check your credentials.';
        this.clearErrorMessage();
      }
    });
  }

  getAdminWithCredential(username: string, password: string): void {
    this.adminService.getAdminWithCredentials(username, password).subscribe({
      next: data => {
        this.admin = data;
        this.adminService.setAdmin(data)
        this.router.navigate(['/adminAccount']);
      },
      error: err => {
        console.error('Sign in failed ' + username + " " + password);
        this.admin = null;
        this.errorMessage = '';
      }
    })
  }

  onSubmitSignIn(): void {
    this.getAdminWithCredential(this.signInEmail, this.signInPassword);
    if (!this.admin) {
      this.getUserWithCredentials(this.signInEmail, this.signInPassword);
    }
  }

  onSubmitSignUp(form: NgForm): void {
    this.userService.getUserByEmail(this.signUpEmail).subscribe({
      next: data => {
        this.errorMessage = 'An account with that email already exists';
        this.clearErrorMessage();
      },
      error: err => {
        this.errorMessage = '';
        this.addUser();
      }
    });
  }

  addUser(): void {
    const today = new Date();
    const newUser: User = {
      email: this.signUpEmail,
      password: this.signUpPassword,
      username: this.signUpUsername,
      lastPaymentDate: today
    };
    this.userService.addUser(newUser).subscribe({
      next: data => {
        this.user = data;
        this.userService.setUser(this.user);
        this.router.navigate(['/account']);
      },
      error: err => {
        console.error('Sign up failed', err);
        this.user = null;
        this.errorMessage = 'Sign up failed. Please try again.';
        this.clearErrorMessage();
      }
    });
  }

  clearErrorMessage(): void {
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
  }
}
