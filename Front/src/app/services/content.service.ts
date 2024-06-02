import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BillingDetails} from "../models/billingDetails";
import {BehaviorSubject, Observable} from "rxjs";
import {Admin} from "../models/admin";
import {User} from "../models/user";
import {SignIn} from "../models/signIn";
import {Content} from "../models/content";

const apiUrl = "http://localhost:8080/content";

@Injectable({
  providedIn: 'root'
})
export class ContentService {
  private contentSubject = new BehaviorSubject<Content | null>(null);
  constructor(private http: HttpClient) {
  }

  getAllContent() : Observable<Content[]> {
    console.log("Get all content");
    return this.http.get<Content[]>(`${apiUrl}/all`);
  }

  getContentByAdmin(adminId: string): Observable<Content[]> {
    console.log("Get content by admin");
    return this.http.get<Content[]>(`${apiUrl}/getAllContentAdmin/${adminId}`);
  }

  getContentById(id: string): Observable<Content> {
    console.log("Get content by id");
    return this.http.get<Content>(`${apiUrl}/find/${id}`);
  }

  addContent(content: Content): Observable<Content> {
    console.log("Add content");
    return this.http.post<Content>(`${apiUrl}/add`, content);
  }

  modifyContent(content: Content): Observable<Content> {
    console.log("Update content");
    return this.http.post<Content>(`${apiUrl}/update`, content);
  }

  deleteContent(id: string): Observable<Content> {
    console.log("Delete content");
    return this.http.delete<Content>(`${apiUrl}/delete/${id}`);
  }

  getContent() {
    return this.contentSubject.asObservable();
  }

  setContent(content: Content): void {
    this.contentSubject.next(content);
  }
}
