import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../corecontrol/models/user';
import { LoginRequest } from '../corecontrol/payloads/login-request';
import { TokenStorageService } from '../corecontrol/services/token-storage.service';
import { UserService } from '../corecontrol/services/user.service';
import { AuthResponse } from '../corecontrol/payloads/auth-response';
import { stringify } from 'querystring';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Customvalidators } from '../corecontrol/validators/customvalidators';
import { SignupRequest } from '../corecontrol/payloads/signup-request';
import Swal from 'sweetalert2'


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  formSignup: FormGroup;
  isLoggedIn = false;
  isLoginFailed = false;
  inLogin = false;
  errorMessage = '';
  token: string;
  rolename: string;
  user: User = new User();
  authResponse: AuthResponse;

  checkUser: User = new User();
  //roles: string[] = [];
  private loginRequest: LoginRequest;
  private signupRequest: SignupRequest;

  constructor(private tokenStorage: TokenStorageService, private userService: UserService, private router: Router, private formBuilder: FormBuilder) { }

  onSubmit() {
    console.log(this.form);

    this.loginRequest = new LoginRequest(
      this.form.email,
      this.form.password);
    console.log(this.form.email);

    this.userService.login(this.loginRequest).subscribe(
      (data) => {
        console.log(data);
        localStorage.setItem('token', JSON.stringify(data));

        this.inLogin = true;
        localStorage.setItem('inLogin', JSON.stringify(this.inLogin));

        this.userService.getUserByEmail(this.loginRequest.email).subscribe(data => {
          console.log("user: ", data);
          this.user = data;
          //this.authResponse.user = this.user;
          //localStorage.setItem('auth',JSON.stringify(this.authResponse) );
          localStorage.setItem('currentuser', JSON.stringify(this.user));

          //check user role: ROLE_USER and navigate to Home page
          this.userService.check(this.loginRequest.email).subscribe(
            (data) => {
              if (data == 'ROLE_USER') {
                console.log("ktra dc customer");
                this.gotoList();
              }
              else {
                this.gotoAdmin();
              }
            });

        })

        Swal.fire(
          'Đăng nhập thành công!',
          'success'
        );  
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
        Swal.fire(
          'Lỗi đăng nhập!',
          'Mời bạn kiểm tra lại email, password.',
          'error'
        );
      }
    );




  }
  gotoList() {
    //location.reload();
    location.replace('');
    this.router.navigate(['']);
  }

  gotoAdmin() {
    this.router.navigate(['/admin/adproduct']);
  }

  onSubmitSignup(formSignup: FormGroup) {
    this.signupRequest = new SignupRequest(
      this.formSignup.controls['name'].value,
      this.formSignup.controls['email'].value,
      this.formSignup.controls['password'].value);
    console.log(this.form.email);

    this.userService.signup(this.signupRequest).subscribe(
      (data) => {
        console.log(data);
        Swal.fire(
          'Đăng kí thành công!',
          'success'
        ); 

        formSignup.reset();
        location.reload();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
        Swal.fire(
          'Email đã tồn tại!',
          'Mời bạn đăng kí lại.',
          'error'
        );
      }
    );



  }
  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;

      //this.roles = this.tokenStorage.getAuthorities();
    }
    localStorage.setItem('inLogin', 'false');

    this.formSignup = this.formBuilder.group({
      name: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6), Validators.pattern('^[a-zA-Z0-9_.-]{6,20}$')]),
      password2: new FormControl('', Validators.required),

    }, { validators: Customvalidators.passwordMatchValidator });
  }



}
