import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: User = new User();
  constructor() { }

  ngOnInit() {

    //console.log(localStorage.getItem('currentName'));
    this.user = JSON.parse(localStorage.getItem('currentUser'));
        console.log("luu user from localStorage: " + this.user.name);
  }

}
