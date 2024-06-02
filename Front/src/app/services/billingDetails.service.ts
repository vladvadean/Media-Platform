import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BillingDetails} from "../models/billingDetails";
import {Observable} from "rxjs";
import {User} from "../models/user";
import {considerSettingUpAutocompletion} from "@angular/cli/src/utilities/completion";

const apiUrl = "http://localhost:8080/billingDetails";

@Injectable({
  providedIn: 'root'
})
export class BillingDetailsService {
  constructor(private http: HttpClient) {
  }

  getBillingDetailsOfUser(userId: string): Observable<BillingDetails[]> {
    console.log("Get billing details");
    return this.http.get<BillingDetails[]>(`${apiUrl}/find/${userId}`);
  }

  getMostRecentBill(billingDetails: BillingDetails[]) : BillingDetails{
    billingDetails.sort((a, b) => {
      return new Date(b.transactionDate).getTime() - new Date(a.transactionDate).getTime();
    });

    return billingDetails[0];
  }

  addBillingDetails(billingDetails: BillingDetails) {
    return this.http.post<BillingDetails>(`${apiUrl}/add`, billingDetails);
  }
}
