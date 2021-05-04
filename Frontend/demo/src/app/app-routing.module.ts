import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForgotPasswordComponent } from './Component/forgot-password/forgot-password.component';
import { LoginComponent } from './Component/login/login.component';
import { SignupComponent } from './Component/signup/signup.component';
import { UpdatePasswordComponent } from './Component/update-password/update-password.component';
import { UserListComponent } from './Component/user-list/user-list.component';

const routes: Routes = [
  {
    path: '', redirectTo: "/login", pathMatch: 'full' // Default page
  },
  { path: 'login', component: LoginComponent,  },
  { path: 'signup', component: SignupComponent },
  { path: 'forgotPassword', component: ForgotPasswordComponent },
  { path: 'user-list', component: UserListComponent },
  { path: 'update-password', component: UpdatePasswordComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
