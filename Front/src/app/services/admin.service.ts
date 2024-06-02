import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BillingDetails} from "../models/billingDetails";
import {BehaviorSubject, Observable} from "rxjs";
import {Admin} from "../models/admin";
import {User} from "../models/user";
import {SignIn} from "../models/signIn";
import {LikedContent} from "../models/likedContent";

const apiUrl = "http://localhost:8080/admin";

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private adminSubject = new BehaviorSubject<Admin | null>(null);
  constructor(private http: HttpClient) {
  }

  getAdminByUsername(username: string): Observable<Admin> {
    console.log("Get admin details");
    return this.http.get<Admin>(`${apiUrl}/username/${username}`);
  }

  getAdminWithCredentials(email: string, password: string): Observable<Admin> {
    console.log("Get admin with credentials");
    console.log("! " + email + " " + password);
    const signIn: SignIn = {email, password};
    return this.http.post<Admin>(`${apiUrl}/sign-in`, signIn);
  }


  deleteAdmin(admin: Admin): Observable<void> {
    return this.http.delete<void>(`${apiUrl}/delete/${admin.id}`);
  }

  getAllAdmins(): Observable<Admin[]> {
    return this.http.get<Admin[]>(`${apiUrl}/all`);
  }



  addAdmin(admin: Admin | null) {
    return this.http.post<BillingDetails>(`${apiUrl}/add`, admin);
  }

  getAdmin() {
    return this.adminSubject.asObservable();
  }

  setAdmin(admin: Admin): void {
    this.adminSubject.next(admin);
  }
}
