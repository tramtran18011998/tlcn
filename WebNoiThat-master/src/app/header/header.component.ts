import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: User = JSON.parse(localStorage.getItem('currentUser'));
  currentName: string= this.user.name;
  //this.user = JSON.parse(localStorage.getItem('currentUser'));
  constructor() { }
  //currentName: string=localStorage.getItem('currentName');

  ngOnInit() {
  }

}
