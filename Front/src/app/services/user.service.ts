import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable, of} from "rxjs";
import {User} from "../models/user";
import {SignIn} from "../models/signIn";

const apiUrl = "http://localhost:8080/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userSubject = new BehaviorSubject<User | null>(null);

  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<User[]> {
    console.log("Get users");
    return this.http.get<User[]>(`${apiUrl}/all`);
  }

  getUserWithCredentials(email: string, password: string): Observable<User> {
    console.log("Get user with credentials");
    console.log("! " + email + " " + password);
    const signIn: SignIn = {email, password};
    return this.http.post<User>(`${apiUrl}/sign-in`, signIn);
  }

  deleteUser(user: User): Observable<User> {
    return this.http.delete<User>(`${apiUrl}/delete/${user.id}`);
  }

  getUserByEmail(email: string): Observable<User> {
    return this.http.get<User>(`${apiUrl}/email/${email}`);
  }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(`${apiUrl}/add`, user);
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${apiUrl}/update`, user);
  }

  getUser() {
    return this.userSubject.asObservable();
  }

  setUser(user: User): void {
    this.userSubject.next(user);
  }

}
