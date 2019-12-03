import { Component, OnInit } from '@angular/core';
import { LoginRequest } from '../payloads/login-request';
import { TokenStorageService } from '../services/token-storage.service';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { User } from '../models/user';

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

  checkUser: User = new User();
  //roles: string[] = [];
  private loginRequest: LoginRequest;

  constructor(private tokenStorage: TokenStorageService, private userServvice: UserService, private router: Router) { }

  onSubmit() {
    console.log(this.form);

    this.loginRequest = new LoginRequest(
      this.form.email,
      this.form.password);

    // this.userServvice.login(this.loginRequest).subscribe(
    //   data => {console.log(data); localStorage.setItem('currentUser', JSON.stringify(data))},
    //   error => {
    //     console.log(error);
    //     this.errorMessage = error.error.message;
    //     this.isLoginFailed = true;
    //   }
    // );
    
    
     this.userServvice.login(this.loginRequest).subscribe(
      (data) => {
        console.log(data); 
        localStorage.setItem('currentUser', JSON.stringify(data));
        // this.checkUser = JSON.parse(localStorage.getItem('currentUser'));
        // console.log("luu user from localStorage" + this.checkUser);
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );

    //check user role: ROLE_USER and navigate to Home page
    this.userServvice.check(this.loginRequest.email).subscribe(
      (data) => {      
        if(data == 'ROLE_USER'){
          console.log("ktra dc customer");
          this.gotoList();
        }
      });
    
    console.log("Success");
    
      
    
  }
  gotoList() {
    this.router.navigate(['']);
    
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      
      //this.roles = this.tokenStorage.getAuthorities();
    }
  }

  

}
