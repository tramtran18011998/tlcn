import { Component, OnInit } from '@angular/core';
import { User } from '../corecontrol/models/user';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  checkLogin = false
  //user: User = JSON.parse(localStorage.getItem('currentuser'));
  user: User = new User();
  //currentName: string= this.user.name;
  constructor() { }

  ngOnInit() {
    //localStorage.setItem('inLogin', 'false');
    console.log(this.checkLogin);
    if(localStorage.getItem('currentuser')!=null){
      this.user = JSON.parse(localStorage.getItem('currentuser'));
    }
    if(localStorage.getItem('token')!=null){
      this.checkLogin = true;
    }
  }

  logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('currentuser');
    localStorage.setItem('inLogin','false');
    location.replace('');
  }

}
