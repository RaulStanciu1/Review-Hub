import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'web-ui';
  r_username = "RaulStanciu";
  ldm_mode = localStorage.getItem("ldm_mode");
  app_default = localStorage.getItem("app_default");

  ldmClick() {
    if(this.ldm_mode == "0") {
      this.ldm_mode = "1";
    } else {
      this.ldm_mode = "0"
    }

    localStorage.setItem("ldm_mode", this.ldm_mode);
  }
  
  ngOnInit() {
    if(this.app_default == "true") {
      console.log("App: Default is set!")
    } else {
      localStorage.setItem("ldm_mode", "0");
      this.ldm_mode = localStorage.getItem("ldm_mode");
      localStorage.setItem("app_default", "true");
      this.app_default = localStorage.getItem("app_default");
      console.log("App: Default was setup!")
    }
  }
}
