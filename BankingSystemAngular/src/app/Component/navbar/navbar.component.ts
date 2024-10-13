import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { SettingsComponent } from '../settings/settings.component';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  @ViewChild(SettingsComponent) settingsComponent!: SettingsComponent;

  constructor(private auth: AuthService, private router: Router) {}

  logout(): void {
    this.auth.logOut();
    this.router.navigate(['/auth']);
  }

  openSettings() {
    this.settingsComponent.open();
  }
}
