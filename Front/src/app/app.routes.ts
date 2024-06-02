import {Routes} from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {AccountComponent} from "./components/account/account.component";
import {SignInSignUpComponent} from "./components/sign-in-sign-up/sign-in-sign-up.component";
import {AdminAccountComponent} from "./components/admin-account/admin-account.component";

export const routes: Routes = [
  {path: '', redirectTo: 'signInSignUp', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'account', component: AccountComponent},
  {path: 'signInSignUp', component: SignInSignUpComponent},
  {path: 'adminAccount', component: AdminAccountComponent}
];
