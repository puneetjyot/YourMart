import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth.service';
import { sellerdto } from '../models/sellerdto';

@Component({
  selector: 'app-titlebar',
  templateUrl: './titlebar.component.html',
  styleUrls: ['./titlebar.component.css']
})

export class TitlebarComponent implements OnInit {
  user:any;
  isLoaded:boolean=false;
  constructor(private authService:AuthService) { }

  ngOnInit() {

    this.authService.currentUser()
    .subscribe((data:sellerdto)=>{
      console.log(data);
      this.user=data.data;
      this.isLoaded=true;
    })
  }

}
