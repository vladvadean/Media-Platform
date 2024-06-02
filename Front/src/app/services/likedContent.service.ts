import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { BillingDetails } from "../models/billingDetails";
import { BehaviorSubject, Observable } from "rxjs";
import { Admin } from "../models/admin";
import { SignIn } from "../models/signIn";
import { LikedContent } from "../models/likedContent";
import { Content } from "../models/content";

const apiUrl = "http://localhost:8080/likedContent";

@Injectable({
  providedIn: 'root'
})
export class LikedContentService {
  private likedContentSubject = new BehaviorSubject<LikedContent | null>(null);

  constructor(private http: HttpClient) { }

  getLikedContentOfUser(userId: string): Observable<LikedContent[]> {
    return this.http.get<LikedContent[]>(`${apiUrl}/findUser/${userId}`);
  }

  getContentOfUser(userId: string): Observable<Content[]> {
    return this.http.get<Content[]>(`${apiUrl}/findUserContent/${userId}`);
  }

  getLikedContentById(contentId: string): Observable<LikedContent> {
    return this.http.get<LikedContent>(`${apiUrl}/findContentUser/${contentId}`);
  }

  getLikedContentOfContentAndUser(userId: string, contentId: string): Observable<LikedContent> {
    return this.http.get<LikedContent>(`${apiUrl}/findUserContent/${userId}/${contentId}`);
  }

  addLikedContent(likedContent: LikedContent): Observable<LikedContent> {
    return this.http.post<LikedContent>(`${apiUrl}/add`, likedContent);
  }

  deleteLikedContent(likedContentId: string): Observable<void> {
    return this.http.delete<void>(`${apiUrl}/delete/${likedContentId}`);
  }

  getLikedContent() {
    return this.likedContentSubject.asObservable();
  }

  setLikedContent(likedContent: LikedContent): void {
    this.likedContentSubject.next(likedContent);
  }
}
