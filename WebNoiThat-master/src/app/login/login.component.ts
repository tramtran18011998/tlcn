import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../corecontrol/models/user';
import { LoginRequest } from '../corecontrol/payloads/login-request';
import { TokenStorageService } from '../corecontrol/services/token-storage.service';
import { UserService } from '../corecontrol/services/user.service';
import { AuthResponse } from '../corecontrol/payloads/auth-response';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  token: string;
  rolename: string;
  user: User = new User();
  authResponse: AuthResponse ;

  checkUser: User = new User();
  //roles: string[] = [];
  private loginRequest: LoginRequest;

  constructor(private tokenStorage: TokenStorageService, private userService: UserService, private router: Router) { }

  onSubmit() {
    console.log(this.form);

    this.loginRequest = new LoginRequest(
      this.form.email,
      this.form.password);
      console.log(this.form.email);

    this.userService.login(this.loginRequest).subscribe(
      (data) => {
        console.log(data);
        //this.authResponse = data;
        localStorage.setItem('token',JSON.stringify(data) );
        //localStorage.setItem('currentUser', JSON.stringify(data));
        // this.checkUser = JSON.parse(localStorage.getItem('currentUser'));
        // console.log("luu user from localStorage" + this.checkUser);
        this.userService.getUserByEmail(this.loginRequest.email).subscribe(data => {
          console.log("user: ",data);
          this.user = data;
        })
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
    

    //check user role: ROLE_USER and navigate to Home page
    this.userService.check(this.loginRequest.email).subscribe(
      (data) => {
        if (data == 'ROLE_USER') {
          console.log("ktra dc customer");
          this.gotoList();
        }
        else{
          this.gotoAdmin();
        }
      });

      localStorage.removeItem('currentUser');
    //console.log("Success");



  }
  gotoList() {
    this.router.navigate(['']);
  }

  gotoAdmin() {
    this.router.navigate(['/admin/adproduct']);
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;

      //this.roles = this.tokenStorage.getAuthorities();
    }
  }



}
